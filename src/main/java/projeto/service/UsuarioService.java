package projeto.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import projeto.dto.bean.UserGraficoPizza;

@Service
public class UsuarioService {
	
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	
	public List<UserGraficoPizza> listUserGraficoPizzaAndBar(){
		
		String sql = "select sum(1) as qtde, idade from usuario where idade is not null group by idade";
		
		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<UserGraficoPizza>(UserGraficoPizza.class));
		
	}
	
	
	
	public List<UserGraficoPizza> listUserCrossBar(){
		
		String sql = "select sum(1) as qtde, idade, estado from usuario where idade is not null group by idade, estado order by estado";
		
		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<UserGraficoPizza>(UserGraficoPizza.class));
		
	}

}
