package projeto.jasperreport.controllers;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.DatatypeConverter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import projeto.jasperreport.ReportUtil;
import projeto.repository.UsuarioRepository;
import projeto.service.UsuarioService;

@Controller
public class ImpressaoReportController {

    @Autowired
    private ReportUtil reportUtil;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private UsuarioService usuarioService;

    /* Para download em arquivo PDF */
    @RequestMapping(value = "/imprimirreport/nome/{nome}/salario_ini/{salario_ini}/salario_fim/{salario_fim}", method = RequestMethod.GET)
    public void imprimirReport(@PathVariable("nome") String nome, @PathVariable("salario_ini") Double salario_ini,
            @PathVariable("salario_fim") Double salario_fim, HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        ServletContext context = request.getServletContext();
        HashMap<String, Object> params = new HashMap<>();
        List<?> listDados = new ArrayList<>();

        listDados = montaDados(nome, salario_ini, salario_fim);

        byte[] reportByte = reportUtil.gerarRelatorioByte(params, context, nome, listDados);

        response.setContentType("application/pdf");
        response.setContentLength(reportByte.length);
        response.setHeader("Content-Disposition", "attachment;filename=report.pdf");

        OutputStream outputStream = response.getOutputStream();
        outputStream.write(reportByte);
        outputStream.flush();
        outputStream.close();
    }

    /* Para download em arquivo PDF em base 64 para API REST */
    @ResponseBody
    @RequestMapping(value = "/imprimirReportBase64/nome/{nome}/salario_ini/{salario_ini}/salario_fim/{salario_fim}", method = RequestMethod.GET)
    public ResponseEntity<String> imprimirReportBase64(@PathVariable("nome") String nome,
            @PathVariable("salario_ini") Double salario_ini, @PathVariable("salario_fim") Double salario_fim,
            HttpServletRequest request, HttpServletResponse response) throws Exception {

        ServletContext context = request.getServletContext();
        HashMap<String, Object> params = new HashMap<>();
        List<?> listDados = new ArrayList<>();

        listDados = montaDados(nome, salario_ini, salario_fim);

        byte[] reportByte = reportUtil.gerarRelatorioByte(params, context, nome, listDados);

        String base64 = "data:application/pdf;base64," + DatatypeConverter.printBase64Binary(reportByte);

        return new ResponseEntity<>(base64, HttpStatus.OK);
    }

    private List<?> montaDados(String nome, Double salario_ini, Double salario_fim) {
        List<?> listDados = new ArrayList<>();
        if (nome.equals("relatorio-usuario-jrbcds") || nome.equals("relatorio-usuario-agrupamento-jrbcds")
                || nome.equals("relatorio-usuario-com-subreport-jrbcds")) {
            listDados = usuarioRepository.listbySalario(salario_ini, salario_fim);
        } else if (nome.equals("relatorio-usuario-grafico-pizza-jrbcds")
                || nome.equals("relatorio_usuario-barra-chart-jrbcds")) {
            listDados = usuarioService.listUserGraficoPizzaAndBar();
        } else if (nome.equals("relatorio_usuario_tabela-jrbcds")) {
            listDados = usuarioRepository.findAll();
        } else if (nome.equals("relatorio_usuario_crosstable-jrbcds")) {
            listDados = usuarioService.listUserCrossBar();
        }
        return listDados;
    }

    @RequestMapping(value = "/imprime-report-mvc", method = RequestMethod.GET)
    public String imprimeReportMvc() {
        return "imprime-report-mvc.html"; // Supondo que isso seja um template ou página para exibir algo no MVC
    }

    @RequestMapping(value = "/imprime-report-rest-api", method = RequestMethod.GET)
    public String imprimeReportRetApi() {
        return "imprime-report-rest-api.html"; // Supondo que isso seja um template ou página para exibir algo na API REST
    }

    @RequestMapping(value = "/imprimirReportMvc", method = RequestMethod.POST)
    public String imprimirReportMvc(@RequestParam("nome") String nome, @RequestParam("salario_ini") Double salario_ini,
            @RequestParam("salario_fim") Double salario_fim, HttpServletRequest request,
            HttpServletResponse response) throws Exception {

        // Redireciona para o método GET que faz o download do relatório
        return "redirect:/imprimirreport/nome/" + nome + "/salario_ini/" + salario_ini + "/salario_fim/" + salario_fim;
    }
}
