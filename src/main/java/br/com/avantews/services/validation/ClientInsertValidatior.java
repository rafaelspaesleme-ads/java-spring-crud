package br.com.avantews.services.validation;

import br.com.avantews.domain.Cliente;
import br.com.avantews.domain.enums.TipoCliente;
import br.com.avantews.dto.ClienteNewDTO;
import br.com.avantews.repositories.ClienteRepository;
import br.com.avantews.resource.exception.FieldMessage;
import br.com.avantews.services.validation.util.BR;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.validation.ConstraintValidatorContext;

public class ClientInsertValidatior implements ConstraintValidator<ClientInsert, ClienteNewDTO> {

    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public void initialize(ClientInsert clientInsert) {
    }

    @Override
    public boolean isValid(ClienteNewDTO clienteNewDTO, ConstraintValidatorContext context) {
        List<FieldMessage> list = new ArrayList<>();

        // inclua os testes aqui, inserindo erros na lista
        List<FieldMessage> fieldMessages = new ArrayList<>();

        if (clienteNewDTO.getTipo().equals(TipoCliente.PESSSOAFISICA.getCodigo()) && !BR.isValidCPF(clienteNewDTO.getCpfCnpj())) {

            list.add(new FieldMessage("cpfOuCnpj", "CPF Invalido"));

        }

        if (clienteNewDTO.getTipo().equals(TipoCliente.PESSOAJURIDICA.getCodigo()) && !BR.isValidCNPJ(clienteNewDTO.getCpfCnpj())) {

            list.add(new FieldMessage("cpfOuCnpj", "CNPJ Invalido"));

        }

        Optional<Cliente> clienteRecebeEmail = clienteRepository.findByEmail(clienteNewDTO.getEmail());
        Optional<Cliente> clienteRecebeCpfCnpj = clienteRepository.findByCpfOuCnpj(clienteNewDTO.getCpfCnpj());

        if (clienteRecebeEmail.isPresent()) {

                list.add(new FieldMessage("email", "Email já existente!"));

        }

        if (clienteRecebeCpfCnpj.isPresent()) {

                list.add(new FieldMessage("cpfCnpj", "CPF ou CNPJ já existente!"));

        }

        for (FieldMessage e : list) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getMessage())
                    .addPropertyNode(e.getFieldName()).addConstraintViolation();
        }
        return list.isEmpty();
    }
}
