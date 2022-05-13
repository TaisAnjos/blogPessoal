package org.generation.blogpessoal.repository;


import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;


import static org.junit.jupiter.api.Assertions.assertEquals;
import org.generation.blogpessoal.model.Usuario;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;


//indicando que a classe UsuarioRepositoryTest é uma classe de test, que vai rodar em uma porta aleatoria a cada teste realizado
//indicando que a classe UsuarioRepositoryTest é uma classe de test, que vai rodar em uma porta aleatoria a cada teste realizado
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)

//cria uma instancia de testes, que define que o ciclo de vida do teste vai respeitar o ciclo de vida da classe(será executado e resetado após o uso)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UsuarioRepositoryTest {

    @Autowired
    private UsuarioRepository repository;

    @BeforeAll
    void start() {

        repository.save(new Usuario(1L, "Maiar", "isadora@gmail.com", "51 e pinga", "https://i.imgur.com/FETvs2O.jpg"));

        repository.save(new Usuario(2L, "Michael", "michaeltrimundial@gmail.com", "nunca fui rebaixado",
                "https://i.imgur.com/FETvs2O.jpg"));

        repository.save(new Usuario(3L, "Brocco", "broco@gmail.com", "broccolis", "https://i.imgur.com/FETvs2O.jpg"));

        repository
                .save(new Usuario(4L, "Mayara", "will31smith@gmail.com", "cenoura", "https://i.imgur.com/FETvs2O.jpg"));
    }

    @Test
    @DisplayName("Teste que retorna 1 usuario")
    
    public void retornaUmUsuario() {
        Optional<Usuario> usuario = repository.findByUsuario("isadora@gmail.com");
        assertTrue(usuario.get().getUsuario().equals("isadora@gmail.com"));
    }
    
    @AfterAll
	public void end() {
		repository.deleteAll();
	}
    
    @Test
    @DisplayName ("retorna 3 usuarios")
    
    public void deveRetornarTresUsuarios () {
    List <Usuario> listaDeUsuarios  = repository.findAllByUsuario  ("@gmail.com");
    		assertEquals (3, listaDeUsuarios.size());
            assertTrue(listaDeUsuarios.get(4).getUsuario().equals("will31smith@gmail.com"));
            assertTrue(listaDeUsuarios.get(1).getUsuario().equals("isadora@gmail.com"));
            assertTrue(listaDeUsuarios.get(3).getUsuario().equals("brocco@gmail.com"));
    }
}