package com.triersistemas.restaurante.service.impl;

import com.triersistemas.restaurante.dto.FuncionarioDto;
import com.triersistemas.restaurante.entity.FuncionarioEntity;
import com.triersistemas.restaurante.entity.RestauranteEntity;
import com.triersistemas.restaurante.enuns.CargoEnum;
import com.triersistemas.restaurante.repository.FuncionarioRepository;
import com.triersistemas.restaurante.service.FuncionarioService;
import com.triersistemas.restaurante.service.RestauranteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;

@Service
public class FuncionarioServiceImpl implements FuncionarioService {

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Autowired
    private RestauranteService restauranteService;


    @Override
    public FuncionarioDto postFuncionario(FuncionarioDto funcionarioDto) {

        adequaCpf(funcionarioDto);
        validaCargaHoraria(funcionarioDto.getCargaHoraria());
        validaSalario(funcionarioDto.getCargo(),funcionarioDto.getSalario());
        validaIdade(funcionarioDto.getDataNascimento());

        var restaurante = getRestaurante(funcionarioDto.getRestauranteId());

        var funcionarioEntity = funcionarioRepository.save(new FuncionarioEntity(funcionarioDto, restaurante));

        return new FuncionarioDto(funcionarioEntity);
    }

    @Override
    public List<FuncionarioDto> getAllFuncionarios() {
        return funcionarioRepository.findAll().stream().map(FuncionarioDto::new).toList();
    }

    @Override
    public FuncionarioDto getFuncionario(Long id) {
        return new FuncionarioDto(getFuncionarioEntity(id));
    }

    @Override
    public FuncionarioEntity getFuncionarioEntity(Long id) {
        return funcionarioRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("ERRO: Funcionário não existe"));
    }

    @Override
    public FuncionarioDto putFuncionario(Long id, FuncionarioDto funcionarioDto) {

        adequaCpf(funcionarioDto);
        validaCargaHoraria(funcionarioDto.getCargaHoraria());
        validaSalario(funcionarioDto.getCargo(),funcionarioDto.getSalario());
        validaIdade(funcionarioDto.getDataNascimento());

        var restaurante = getRestaurante(funcionarioDto.getRestauranteId());
        var funcionarioEntity = getFuncionarioEntity(id);

        funcionarioEntity.putRegistro(funcionarioDto, restaurante);

        return new FuncionarioDto(funcionarioRepository.save(funcionarioEntity));
    }

    @Override
    public void deleteFuncionario(Long id) {
        funcionarioRepository.deleteById(id);
    }

    public RestauranteEntity getRestaurante(Long id) {
        return restauranteService.getRestauranteEntity(id);
    }

    //Validações

    private void validaCargaHoraria(Integer horas) {
        if (horas > 220) {
            throw new IllegalArgumentException("ERRO: Carga horária acima do limite de 220 horas");
        }
    }

    private void validaSalario(CargoEnum cargo ,BigDecimal salario) {
        if (salario.compareTo(BigDecimal.valueOf(1500)) < 0 && !CargoEnum.FREELANCER.equals(cargo)) {
            throw new IllegalArgumentException("ERRO: O funcionário deve receber mais que um salário minímo");
        }
    }

    private void validaIdade(LocalDate dataNascimento) {
        Period periodo = Period.between(dataNascimento, LocalDate.now());
        if (periodo.getYears() > 100 || periodo.getYears() < 12) {
            throw new IllegalArgumentException("ERRO: A pessoa não pode ter mais de 100 anos e menos de 12");
        }
    }

    private void adequaCpf(FuncionarioDto funcionarioDto){

        funcionarioDto.setCpf(funcionarioDto.getCpf().replaceAll("\\D", ""));

        if (funcionarioDto.getCpf().length() != 11) {
            throw new IllegalArgumentException("ERRO: O CPF deve ter 11 números!");
        }
    }

}
