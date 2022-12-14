    package br.edu.uepb.projeto1.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.uepb.projeto1.domain.Aluno;
import br.edu.uepb.projeto1.exceptions.ExistingSameNameException;
import br.edu.uepb.projeto1.repository.AlunoRepository;
import javassist.NotFoundException;

@Service
public class AlunoService {

    @Autowired
    private AlunoRepository alunoRepository;

    public Aluno createAluno(Aluno aluno) throws ExistingSameNameException {
        if (alunoRepository.findByNome(aluno.getNome()).isPresent())
            throw new ExistingSameNameException("Já existe um aluno com esse nome!");
        return alunoRepository.save(aluno);
    }

    public Aluno updateAluno(Long id, Aluno aluno) {
        aluno.setId(id);
        return alunoRepository.save(aluno);
    }

    public List<Aluno> listAllAlunos() {
        return alunoRepository.findAll();
    }

    public Aluno findById(Long id) throws NotFoundException {
        return alunoRepository.findById(id).orElseThrow(() -> new NotFoundException("Não existe um aluno com esse identificador!"));
    }

    public void deleteAluno(Long id) {
        Aluno alunoToDelete = alunoRepository.findById(id).get();
        alunoRepository.delete(alunoToDelete);
    }
}