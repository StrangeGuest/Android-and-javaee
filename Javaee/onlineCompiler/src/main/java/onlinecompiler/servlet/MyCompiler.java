package onlinecompiler.servlet;

import onlinecompiler.compiler.CustomStringJavaCompiler;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

@WebServlet("/mycompiler")
public class MyCompiler extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("UTF-8");
        String content = request.getParameter("content");
        File file = new File("C:\\Users\\26615\\Desktop\\2.txt");//保存文件地址
        try (FileOutputStream fop = new FileOutputStream(file)) {
            if (!file.exists()) {
                file.createNewFile();
            }
            byte[] contentInBytes = content.getBytes();
            fop.write(contentInBytes);
            fop.flush();
            fop.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


        //读取文件内容
        File file2 = new File("C:\\Users\\26615\\Desktop\\2.txt");//定义一个file对象，用来初始化FileReader
        FileReader reader = null;//定义一个fileReader对象，用来初始化BufferedReader
        try {
            reader = new FileReader(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        BufferedReader bReader = new BufferedReader(reader);//new一个BufferedReader对象，将文件内容读取到缓存
        StringBuilder sb = new StringBuilder();//定义一个字符串缓存，将字符串存放缓存中
        String s = "";

        while ((s =bReader.readLine()) != null) {//逐行读取文件内容，不读取换行符和末尾的空格
            sb.append(s + "\n");//将读取的字符串添加换行符后累加存放在缓存中s
        }
        bReader.close();
        String str = sb.toString();
        System.out.println(str);
//        String code = "public class HelloWorld {\n" +
////                "    public static void main(String []args) {\n" +
////                "\t\tfor(int i=0; i < 1; i++){\n" +
////                "\t\t\t       System.out.println(\"Hello World!\");\n" +
////                "\t\t}\n" +
////                "    }\n" +
////                "}";
        CustomStringJavaCompiler compiler = new CustomStringJavaCompiler(str);
        boolean res = compiler.compiler();
        if (res) {
            System.out.println("编译成功");
           // request.setAttribute("result","编译成功");
            System.out.println("compilerTakeTime：" + compiler.getCompilerTakeTime());
            try {
                compiler.runMainMethod();
                System.out.println("runTakeTime：" + compiler.getRunTakeTime());
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println(compiler.getRunResult());
            request.setAttribute("result",compiler.getRunResult());
            System.out.println("诊断信息：" + compiler.getCompilerMessage());
        } else {
            System.out.println("编译失败");
            //request.setAttribute("result","编译失败");
            System.out.println(compiler.getCompilerMessage());
        }
        request.getRequestDispatcher("compiler.jsp").forward(request,response);

    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
