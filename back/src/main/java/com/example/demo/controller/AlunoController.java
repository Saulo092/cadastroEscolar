package com.example.demo.controller;
import com.example.demo.model.Aluno;
import com.example.demo.repository.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import org.springframework.http.ResponseEntity;
@CrossOrigin(origins = {"http://localhost:5173"}) // Endereço do front
@RestController

@RequestMapping("/aluno")
public class AlunoController {
    @Autowired
    private AlunoRepository alunoRepository;

    @GetMapping
    public List<Aluno> listarAlunos() {
        return alunoRepository.findAll();
    }

    @PostMapping
    public Aluno criarAluno(@RequestBody Aluno aluno) {
        return alunoRepository.save(aluno);
    }

    @DeleteMapping("/{id_aluno}")
    public ResponseEntity<String> deletarAluno(@PathVariable Long id_aluno) {
        if (alunoRepository.existsById(id_aluno)) {
            alunoRepository.deleteById(id_aluno);
            return ResponseEntity.ok("Aluno deletado com sucesso");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id_aluno}")
    public ResponseEntity<Aluno> atualizarAluno(@PathVariable Long id_aluno, @RequestBody Aluno alunoAtualizado) {
        if (alunoRepository.existsById(id_aluno)) {
            Aluno aluno = alunoRepository.findById(id_aluno).get();
            aluno.setNomeCompleto(alunoAtualizado.getNomeCompleto());
            aluno.setDataNascimento(alunoAtualizado.getDataNascimento());
            aluno.setNumeroMatricula(alunoAtualizado.getNumeroMatricula());
            aluno.setEmail(alunoAtualizado.getEmail());
            aluno.setTelefone(alunoAtualizado.getTelefone());
            aluno.setEndereco(alunoAtualizado.getEndereco());
            Aluno alunoAtualizadoBD = alunoRepository.save(aluno);
            return ResponseEntity.ok(alunoAtualizadoBD);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
