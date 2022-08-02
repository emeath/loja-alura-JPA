package br.com.alura.loja.testes;

import br.com.alura.loja.dao.CategoriaDao;
import br.com.alura.loja.dao.ProdutoDao;
import br.com.alura.loja.modelo.Categoria;
import br.com.alura.loja.modelo.Produto;
import br.com.alura.loja.util.JPAUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.math.BigDecimal;
import java.util.List;

public class CadastroDeProduto {
    public static void main(String[] args) {
        cadastrarProduto();

        buscandoPorId();

        buscandoTodos();

    }

    private static void buscandoTodos() {
        EntityManager em = JPAUtil.getEntityManager();
        ProdutoDao produtoDao = new ProdutoDao(em);
        List<Produto> todosProdutos = produtoDao.buscarTodos();
        todosProdutos.forEach(p -> System.out.println(p.getNome()));
    }

    private static void buscandoPorId() {
        EntityManager em = JPAUtil.getEntityManager();
        ProdutoDao produtoDao = new ProdutoDao(em);
        Produto p = produtoDao.buscaPorId(1L);
        System.out.println(p.getDescricao());
    }

    private static void cadastrarProduto() {
        Categoria celulares = new Categoria("CELULARES");
        Produto celular = new Produto("Xiomi Redmi", "Muito bonm!", new BigDecimal("800.21"), celulares);

        Categoria livros = new Categoria("LIVROS");
        Produto livro = new Produto("Clean Code", "Livro de tecnologia", new BigDecimal(57.2), livros);

        EntityManager em = JPAUtil.getEntityManager();
        ProdutoDao produtoDao = new ProdutoDao(em);
        CategoriaDao categoriaDao = new CategoriaDao(em);

        em.getTransaction().begin();
        categoriaDao.cadastrar(celulares);
        categoriaDao.cadastrar(livros);
        produtoDao.cadastrar(celular);
        produtoDao.cadastrar(livro);
        em.getTransaction().commit();
        em.close();
    }

}
