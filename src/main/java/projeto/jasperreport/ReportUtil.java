package projeto.jasperreport;

import java.io.File;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Component
public class ReportUtil {
	
	private static final String FOLDER_RELATORIOS = "/relatorios";
	private static final String PARAMETRO_PASTA_REPORT = "PARAMETRO_PASTA_REPORT";
	private static final String SEPARETOR = File.separator;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@SuppressWarnings({"unchecked", "rawtypes"})
	public String gerarRelatorioStringPath(HashMap parametroRelatorio, ServletContext servletContext, String nomeReport) throws Exception {
		
		String caminhoPastaImagensReport = servletContext.getRealPath(FOLDER_RELATORIOS);
		parametroRelatorio.put(PARAMETRO_PASTA_REPORT, caminhoPastaImagensReport + SEPARETOR);
		
		JasperPrint impressoraJasper = JasperFillManager
				.fillReport(caminhoPastaImagensReport + SEPARETOR + nomeReport + ".jasper",
				parametroRelatorio, jdbcTemplate.getDataSource().getConnection());
		
		String caminhoArquivoRelatorio = servletContext.getRealPath("") + SEPARETOR + "relatorio.pdf";
		
		JasperExportManager.exportReportToPdfFile(impressoraJasper, caminhoArquivoRelatorio);
		
		return caminhoArquivoRelatorio;
	}
	
	@SuppressWarnings({"unchecked", "rawtypes"})
	public String gerarRelatorioStringPath(HashMap parametroRelatorio, ServletContext servletContext, String nomeReport, List listaDados) throws Exception {
		
		String caminhoPastaImagensReport = servletContext.getRealPath(FOLDER_RELATORIOS);
		parametroRelatorio.put(PARAMETRO_PASTA_REPORT, caminhoPastaImagensReport + SEPARETOR);
		
		JasperPrint impressoraJasper = JasperFillManager
				.fillReport(caminhoPastaImagensReport + SEPARETOR + nomeReport + ".jasper",
				parametroRelatorio, new JRBeanCollectionDataSource(listaDados));
		
		String caminhoArquivoRelatorio = servletContext.getRealPath("") + SEPARETOR + "relatorio.pdf";
		
		JasperExportManager.exportReportToPdfFile(impressoraJasper, caminhoArquivoRelatorio);
		
		return caminhoArquivoRelatorio;
	}
	
	
	@SuppressWarnings({"unchecked","rawtypes"})
	public byte[] gerarRelatorioByte(HashMap parametroRelatorio, ServletContext servletContext, String nomeReport) throws Exception {
		
		String caminhoPastaImagensReport = servletContext.getRealPath(FOLDER_RELATORIOS);
		parametroRelatorio.put(PARAMETRO_PASTA_REPORT, caminhoPastaImagensReport + SEPARETOR);
		
		JasperPrint impressoraJasper = JasperFillManager
				.fillReport(caminhoPastaImagensReport + SEPARETOR + nomeReport + ".jasper",
				parametroRelatorio, jdbcTemplate.getDataSource().getConnection());
		
		return JasperExportManager.exportReportToPdf(impressoraJasper);
		
	}
	
	
	@SuppressWarnings({"unchecked","rawtypes"})
	public byte[] gerarRelatorioByte(HashMap parametroRelatorio, ServletContext servletContext, String nomeReport, List listaDados) throws Exception {
		
		String caminhoPastaImagensReport = servletContext.getRealPath(FOLDER_RELATORIOS);
		parametroRelatorio.put(PARAMETRO_PASTA_REPORT, caminhoPastaImagensReport + SEPARETOR);
		
		JasperPrint impressoraJasper = JasperFillManager
				.fillReport(caminhoPastaImagensReport + SEPARETOR + nomeReport + ".jasper",
				parametroRelatorio, new JRBeanCollectionDataSource(listaDados));
		
		return JasperExportManager.exportReportToPdf(impressoraJasper);
		
	}
	

}
