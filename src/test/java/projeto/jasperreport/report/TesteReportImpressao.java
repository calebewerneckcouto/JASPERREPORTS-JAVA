package projeto.jasperreport.report;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.junit4.SpringRunner;

import projeto.dto.bean.UserGraficoPizza;
import projeto.jasperreport.Application;
import projeto.jasperreport.ReportUtil;
import projeto.model.Usuario;
import projeto.repository.UsuarioRepository;
import projeto.service.UsuarioService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = WebEnvironment.MOCK)
public class TesteReportImpressao {
	
	@Autowired
	private ReportUtil reportUtil;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Test
	public void testeImpressaoRelUsuario1() throws Exception {
		
		HashMap params = new HashMap();
		MockHttpServletRequest request = new MockHttpServletRequest();
		
		params.put("SALARIO_INI", 1.00);
		params.put("SALARIO_FIM" , 500.00);
		String local = reportUtil.gerarRelatorioStringPath(params, request.getServletContext(), "relatorio-usuario");
		
		System.out.println(local);
		
	}
	
	
	@Test
	public void testeImpressaoRelUsuario1Jrbcds() throws Exception {
		
		MockHttpServletRequest request = new MockHttpServletRequest();
		
		List<Usuario> usuarios = usuarioRepository.listbySalario(1.00, 500.00);
		
		String local = reportUtil.gerarRelatorioStringPath(new HashMap(),
				request.getServletContext(), 
				"relatorio-usuario-jrbcds", usuarios);
		
		System.out.println(local);
		
	}
	

	
	@Test
	public void testeImpressaoRelUsuario2() throws Exception {
		
		HashMap params = new HashMap();
		MockHttpServletRequest request = new MockHttpServletRequest();
		
		params.put("SALARIO_INI", 20.00);
		params.put("SALARIO_FIM" , 200.00);
		String local = reportUtil.gerarRelatorioStringPath(params, request.getServletContext(), "relatorio-usuario-agrupamento");
		
		System.out.println(local);
		
	}
	
	@Test
	public void testeImpressaoRelUsuario2jrbcds() throws Exception {
		
		MockHttpServletRequest request = new MockHttpServletRequest();
		
		List<Usuario> usuarios = usuarioRepository.listbySalario(1.00, 500.00);
		
		String local = reportUtil.gerarRelatorioStringPath(new HashMap(),request.getServletContext(), 
				       "relatorio-usuario-agrupamento-jrbcds", usuarios);
		
		System.out.println(local);
		
	}
	
	
	@Test
	public void testeImpressaoRelUsuarioSubreport3() throws Exception {
		
		HashMap params = new HashMap();
		MockHttpServletRequest request = new MockHttpServletRequest();
		
		params.put("SALARIO_INI", 20.00);
		params.put("SALARIO_FIM" , 30.00);
		String local = reportUtil.gerarRelatorioStringPath(params, request.getServletContext(), "relatorio-usuario-com-subreport");
		
		System.out.println(local);
		
	}
	
	
	@Test
	public void testeImpressaoRelUsuarioSubreport3Jrbdcs() throws Exception {
		
		MockHttpServletRequest request = new MockHttpServletRequest();
	
		List<Usuario> usuarios = usuarioRepository.listbySalario(1.00, 500.00);
		
		String local = reportUtil.gerarRelatorioStringPath(new HashMap(),
				   request.getServletContext(), "relatorio-usuario-com-subreport-jrbcds", usuarios);
		
		System.out.println(local);
		
	}
	
	
	@Test
	public void testeImpressaoRelUsuarioGraficoPizza() throws Exception {
		
		
		String local = reportUtil.gerarRelatorioStringPath(new HashMap(),
				new MockHttpServletRequest().getServletContext(),
				"relatorio-usuario-grafico-pizza");
		
		System.out.println(local);
		
	}
	
	@Test
	public void testeImpressaoRelUsuarioGraficoPizzaJrbcds() throws Exception {
		
		
		String local = reportUtil.gerarRelatorioStringPath(new HashMap(),
				new MockHttpServletRequest().getServletContext(),
				"relatorio-usuario-grafico-pizza-jrbcds", usuarioService.listUserGraficoPizzaAndBar());
		
		System.out.println(local);
		
	}
	
	
	@Test
	public void testeImpressaoRelUsuarioGraficoBarra() throws Exception {
		
		
		String local = reportUtil.gerarRelatorioStringPath(new HashMap(),
				new MockHttpServletRequest().getServletContext(),
				"relatorio_usuario-barra-chart");
		
		System.out.println(local);
		
	}
	
	
	@Test
	public void testeImpressaoRelUsuarioGraficoBarraJrbcds() throws Exception {
		
		
		String local = reportUtil.gerarRelatorioStringPath(new HashMap(),
				new MockHttpServletRequest().getServletContext(),
				"relatorio_usuario-barra-chart-jrbcds", usuarioService.listUserGraficoPizzaAndBar());
		
		System.out.println(local);
		
	}
	
	
	@Test
	public void testeImpressaoRelUsuarioTabela() throws Exception {
		
		
		String local = reportUtil.gerarRelatorioStringPath(new HashMap(),
				new MockHttpServletRequest().getServletContext(),
				"relatorio_usuario_tabela");
		
		System.out.println(local);
		
	}
	
	
	
	@Test
	public void testeImpressaoRelUsuarioTabelajrbcds() throws Exception {
		
		
		HashMap params = new HashMap();
		params.put("lista_data_set_user", usuarioService.listUserGraficoPizzaAndBar());
		
		String local = reportUtil.gerarRelatorioStringPath(params,
				new MockHttpServletRequest().getServletContext(),
				"relatorio_usuario_tabela-jrbcds", usuarioRepository.findAll());
		
		System.out.println(local);
		
	}
	
	

	
	@Test
	public void testeImpressaoRelUsuarioCrossTabela() throws Exception {
		
		
		String local = reportUtil.gerarRelatorioStringPath(new HashMap(),
				new MockHttpServletRequest().getServletContext(),
				"relatorio_usuario_crosstable");
		
		System.out.println(local);
		
	}
	
	
	@Test
	public void testeImpressaoRelUsuarioCrossTabelajrbcds() throws Exception {
		
		
		String local = reportUtil.gerarRelatorioStringPath(new HashMap(),
				new MockHttpServletRequest().getServletContext(),
				"relatorio_usuario_crosstable-jrbcds", usuarioService.listUserCrossBar());
		
		System.out.println(local);
		
	}
	
	
	@Test
	public void testeListbyUser() throws Exception {
		
		List<Usuario> usuarios = usuarioRepository.findAll();
		
		String local = reportUtil.gerarRelatorioStringPath(new HashMap(),
				new MockHttpServletRequest().getServletContext(),
				"relatorio-usuario-jrbcds-teste-1", usuarios);
		
		System.out.println(local);
		
		
	}
	
	@Test
	public void testeListbySalario() {
		List<Usuario> usuarios = usuarioRepository.listbySalario(1D, 500D);
		
		for (Usuario usuario : usuarios) {
			System.out.println(usuario);
		}
	}
	
	@Test
	public void testeListUserGraficoPizzaAndBar() {
		 List<UserGraficoPizza> graficoPizzas = usuarioService.listUserGraficoPizzaAndBar();
		 
		 for (UserGraficoPizza userGraficoPizza : graficoPizzas) {
			System.out.println(userGraficoPizza);
		}
	}
	
	
	@Test
	public void testeListUserCrossBar() {
		 List<UserGraficoPizza> graficoPizzas = usuarioService.listUserCrossBar();
		 
		 for (UserGraficoPizza userGraficoPizza : graficoPizzas) {
			System.out.println(userGraficoPizza);
		}
	}

}
