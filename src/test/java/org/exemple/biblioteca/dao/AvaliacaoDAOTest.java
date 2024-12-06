import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import org.exemple.biblioteca.dao.AvaliacaoDAO;
import org.exemple.biblioteca.model.Avaliacao;

import java.sql.SQLException;
import java.util.List;

public class AvaliacaoDAOTest {

    private AvaliacaoDAO avaliacaoDAO;

    @BeforeEach
    void setUp() {
        avaliacaoDAO = new AvaliacaoDAO(); // Instanciar a classe a ser testada.
    }

    @Test
    void testInserirAvaliacao() throws SQLException {
        Avaliacao avaliacao = new Avaliacao(1, "Livro Teste", "Texto de avaliação", "Usuário Teste");
        avaliacaoDAO.inserir(avaliacao);

        List<Avaliacao> avaliacoes = avaliacaoDAO.buscarTodos();
        assertTrue(avaliacoes.stream().anyMatch(a -> a.getAvaliacaoID() == 1),
                "A avaliação inserida não foi encontrada.");
    }

    @Test
    void testBuscarTodos() throws SQLException {
        Avaliacao avaliacao1 = new Avaliacao(2, "Livro A", "Texto A", "Usuário A");
        Avaliacao avaliacao2 = new Avaliacao(3, "Livro B", "Texto B", "Usuário B");

        avaliacaoDAO.inserir(avaliacao1);
        avaliacaoDAO.inserir(avaliacao2);

        List<Avaliacao> avaliacoes = avaliacaoDAO.buscarTodos();
        assertFalse(avaliacoes.isEmpty(), "A lista de avaliações deveria conter itens.");
    }

    @Test
    void testDeletarAvaliacao() throws SQLException {
        Avaliacao avaliacao = new Avaliacao(4, "Livro Deletar", "Texto Deletar", "Usuário Deletar");
        avaliacaoDAO.inserir(avaliacao);

        avaliacaoDAO.deletar(4);
        List<Avaliacao> avaliacoes = avaliacaoDAO.buscarTodos();
        assertTrue(avaliacoes.stream().noneMatch(a -> a.getAvaliacaoID() == 4),
                "A avaliação não foi removida corretamente.");
    }

    @Test
    void testObterProximoAvaliacaoID() throws SQLException {
        int proximoID = avaliacaoDAO.obterProximoAvaliacaoID();
        assertTrue(proximoID > 0, "O próximo ID não é maior que 0.");
    }
}
