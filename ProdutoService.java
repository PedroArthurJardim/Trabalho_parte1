package com.example.estoque.service;

import com.example.estoque.dto.ProdutoRequestDTO;
import com.example.estoque.dto.ProdutoResponseDTO;
import com.example.estoque.entity.Produto;
import com.example.estoque.exception.ResourceNotFoundException;
import com.example.estoque.repository.ProdutoRepository;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProdutoService {

    private final ProdutoRepository produtoRepository;

    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    @Transactional(readOnly = true)
    public List<ProdutoResponseDTO> listar() {
        return produtoRepository.findAll().stream().map(ProdutoResponseDTO::new).toList();
    }

    @Transactional(readOnly = true)
    public ProdutoResponseDTO buscarPorId(Long id) {
        return new ProdutoResponseDTO(buscarEntidade(id));
    }

    @Transactional
    public ProdutoResponseDTO criar(ProdutoRequestDTO request) {
        Produto produto = new Produto();
        atualizarCampos(produto, request);
        return new ProdutoResponseDTO(produtoRepository.save(produto));
    }

    @Transactional
    public ProdutoResponseDTO atualizar(Long id, ProdutoRequestDTO request) {
        Produto produto = buscarEntidade(id);
        atualizarCampos(produto, request);
        return new ProdutoResponseDTO(produtoRepository.save(produto));
    }

    @Transactional
    public void excluir(Long id) {
        produtoRepository.delete(buscarEntidade(id));
    }

    private Produto buscarEntidade(Long id) {
        return produtoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Produto não encontrado com id: " + id));
    }

    private void atualizarCampos(Produto produto, ProdutoRequestDTO request) {
        produto.setNome(request.getNome().trim());
        produto.setPreco(request.getPreco());
        produto.setQuantidadeEstoque(request.getQuantidadeEstoque());
        produto.setCategoria(request.getCategoria());
    }
}
