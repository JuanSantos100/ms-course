package com.devsuperior.hrworker.resources;

import java.util.List;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devsuperior.hrworker.entities.Worker;
import com.devsuperior.hrworker.repositories.WorkerRepository;

@RestController
@RequestMapping(value= "/workers")
public class WorkerResource {
	
	private static Logger logger = org.slf4j.LoggerFactory.getLogger(WorkerResource.class);
	
	@Value("${test.config}")
	private String testConfig;
	
	@Autowired
	private Environment env; //Contém algumas informações de configuração da aplicação
	
	@Autowired
	private WorkerRepository workerRepository;
	
	
	@GetMapping(value = "/configs")
	public ResponseEntity<Void> getConfigs() {
		logger.info("CONFIG = " + testConfig);
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping
	public ResponseEntity<List<Worker>> findAll() {
		List<Worker> workerList = workerRepository.findAll(); //Já vai no banco, traz os dados, instancia a lista e devolve para nós;
		return ResponseEntity.ok(workerList);
	}
	
	@GetMapping(value= "/{id}")
	public ResponseEntity<Worker> findById(@PathVariable Long id) {
		
		/*
		 * Testes realizados com tempo de sleep de 3s
		 * para continuar o processo de request.
		 * */
		try {
			Thread.sleep(3000L);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		logger.info("Port: " + env.getProperty("local.server.port")); //Vai imprimir no server a porta que a aplicação está rodando
		
		Worker obj = workerRepository.findById(id).get(); //Retorna um optional, para pegar o objeto worker dentro do optional, adiciona um .get()
		return ResponseEntity.ok(obj);
	}
	
	
	
}
