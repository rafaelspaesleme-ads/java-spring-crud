package br.com.avantews.services.validation;

import br.com.avantews.domain.enums.TipoCliente;
import br.com.avantews.dto.ClienteNewDTO;
import br.com.avantews.resource.exception.FieldMessage;
import br.com.avantews.services.validation.util.BR;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.ConstraintValidator;

import java.util.ArrayList;
import java.util.List;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ClientInsertValidatior implements ConstraintValidator<ClientInsert, ClienteNewDTO> {
    @Override
    public void initialize(ClientInsert clientInsert) {
    }

    @Override
    public boolean isValid(ClienteNewDTO clienteNewDTO, ConstraintValidatorContext context) {
        List<FieldMessage> list = new ArrayList<>();

        // inclua os testes aqui, inserindo erros na lista
        List<FieldMessage> fieldMessages = new ArrayList<>();

        if (clienteNewDTO.getTipo().equals(TipoCliente.PESSSOAFISICA.getCodigo()) && !BR.isValidCPF(clienteNewDTO.getCpfCnpj())) {

            list.add(new FieldMessage("cpfCnpj", "CPF Invalido"));

        }

        if (clienteNewDTO.getTipo().equals(TipoCliente.PESSOAJURIDICA.getCodigo()) && !BR.isValidCNPJ(clienteNewDTO.getCpfCnpj())) {

            list.add(new FieldMessage("cpfCnpj", "CNPJ Invalido"));

        }

        for (FieldMessage e : list) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getMessage())
                    .addPropertyNode(e.getFieldName()).addConstraintViolation();
        }
        return list.isEmpty();
    }
}
