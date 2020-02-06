package servlet;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(
        name = "MyServlet",
        urlPatterns = {"/hello"}
    )
public class HelloServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String idStr = "";

        Properties properties = new Properties();
        String file = "YourLocalPath/sample.properties";
        try {
            InputStream inputStream = new FileInputStream(file);
            properties.load(inputStream);
            inputStream.close();

            // 値の取得
            idStr = properties.getProperty("id");
            System.out.println(idStr);
            System.out.println(properties.getProperty("password"));

        } catch (Exception ex) {
            System.out.println(ex.getMessage());

        }



        ServletOutputStream out = resp.getOutputStream();
        out.write(idStr.getBytes());
        out.write("\\r\\n".getBytes());
        out.write("hello world".getBytes());
        out.flush();
        out.close();
    }

}
