package br.com.authapi.utils;

import br.com.authapi.dto.employee.EmployeeResponseDTO;
import br.com.authapi.dto.student.StudentResponseDTO;
import br.com.authapi.entities.user.employee.EmployeeDetails;
import br.com.authapi.entities.user.student.StudentDetails;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

public class MapperUtils {

    private static final ModelMapper modelMapper;

    /**
     * Model mapper property setting are specified in the following block.
     * Default property matching strategy is set to Strict see {@link MatchingStrategies}
     * Custom mappings are added using {@link ModelMapper#addMappings(PropertyMap)}
     */
    static {
        modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        //STUDENT RESPONSE
        modelMapper.typeMap(StudentDetails.class, StudentResponseDTO.class).addMappings(mapper -> {
            mapper.map(src -> src.getUser().getId(), StudentResponseDTO::setUserId);
        });
        modelMapper.typeMap(StudentDetails.class, StudentResponseDTO.class).addMappings(mapper -> {
            mapper.map(StudentDetails::getId, StudentResponseDTO::setStudentId);
        });
        modelMapper.typeMap(StudentDetails.class, StudentResponseDTO.class).addMappings(mapper -> {
            mapper.map(src -> src.getUser().fullName(), StudentResponseDTO::setName);
        });

        //EMPLOYEE RESPONSE
        modelMapper.typeMap(EmployeeDetails.class, EmployeeResponseDTO.class).addMappings(mapper -> {
            mapper.map(src -> src.getUser().getId(), EmployeeResponseDTO::setUserId);
        });
        modelMapper.typeMap(EmployeeDetails.class, EmployeeResponseDTO.class).addMappings(mapper -> {
            mapper.map(EmployeeDetails::getId, EmployeeResponseDTO::setEmployeeId);
        });
        modelMapper.typeMap(EmployeeDetails.class, EmployeeResponseDTO.class).addMappings(mapper -> {
            mapper.map(src -> src.getUser().fullName(), EmployeeResponseDTO::setName);
        });
    }

    public <D, T> D map(final T entity, Class<D> dtoClass) {
        return modelMapper.map(entity, dtoClass);
    }

    public <D, T> Page<D> mapEntityPageIntoDtoPage(Page<T> entities, Class<D> dtoClass) {
        return entities.map(objectEntity -> modelMapper.map(objectEntity, dtoClass));
    }

    public <S, T> List<T> mapSet(List<S> source, Class<T> targetClass) {
        return source
                .stream()
                .map(element -> modelMapper.map(element, targetClass))
                .collect(Collectors.toList());
    }

}
