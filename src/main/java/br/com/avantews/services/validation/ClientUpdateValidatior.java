package br.com.avantews.services.validation;

import br.com.avantews.domain.Cliente;
import br.com.avantews.dto.ClienteDTO;
import br.com.avantews.repositories.ClienteRepository;
import br.com.avantews.resource.exception.FieldMessage;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import javax.validation.ConstraintValidatorContext;
import org.springframework.web.servlet.HandlerMapping;

public class ClientUpdateValidatior implements ConstraintValidator<ClientUpdate, ClienteDTO> {

    @Autowired
    private HttpServletRequest httpServletRequest;

    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public void initialize(ClientUpdate clientUpdate) {
    }

    @Override
    public boolean isValid(ClienteDTO clienteDTO, ConstraintValidatorContext context) {

        @SuppressWarnings("unchecked")
        Map<String, String> map = (Map<String, String>) httpServletRequest.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
        Integer uriId = Integer.parseInt(map.get("id"));

        List<FieldMessage> list = new ArrayList<>();

        // inclua os testes aqui, inserindo erros na lista
        List<FieldMessage> fieldMessages = new ArrayList<>();

        Optional<Cliente> clienteRecebeEmail = clienteRepository.findByEmail(clienteDTO.getEmail());

        if (clienteRecebeEmail.isPresent() && !clienteRecebeEmail.get().getId().equals(uriId)) {

                list.add(new FieldMessage("email", "Email já existente!"));

        }

        for (FieldMessage e : list) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getMessage())
                    .addPropertyNode(e.getFieldName()).addConstraintViolation();
        }
        return list.isEmpty();
    }
}
