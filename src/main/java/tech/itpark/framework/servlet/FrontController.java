package tech.itpark.framework.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.UnavailableException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import org.springframework.context.ApplicationContext;
import tech.itpark.framework.http.*;

import java.util.Map;
import java.util.Optional;

// Servlet
public class FrontController extends HttpServlet {
  private Map<String, Map<String, Handler>> routes;
  private RequestResponseReaderWriter rw;
  private final Handler notFoundHandler = (request, response) -> response
          .error(404, "Not found", ContentTypes.TEXT_PLAIN);

  @Override
  public void init() throws ServletException {
    super.init();
    try {
      final var context = (ApplicationContext) getServletContext().getAttribute("CONTEXT");
      rw = context.getBean(RequestResponseReaderWriter.class);
      routes = (Map<String, Map<String, Handler>>) context.getBean("routes");
      // TODO: 1. Annotation Config -> @Component <- your class
      // TODO: 2. Java Config -> @Configuration @Bean <- not your class, initialization logic
    } catch (Exception e) {
      throw new UnavailableException(e.getMessage());
    }
  }

  @Override // in multiple threads
  protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException {
    final var path = request.getServletPath(); // FIXME: RTFM
    final var method = request.getMethod();


    // 1. method -> path
    // 2. path -> method
    try {
      Optional.ofNullable(routes.get(path))
          .map(o -> o.get(method))
          .orElse(notFoundHandler)
          .handle(new ServerRequest(request, rw), new ServerResponse(response, rw));
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
