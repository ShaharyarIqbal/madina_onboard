package com.example.onboard.infrastructure.util;

import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Slf4j
public class ConversationUtil {

    @Autowired
    private ModelMapper modelMapper;


    /**
     * This method is used to convert DTO to Entity.
     *
     * @param sourceDto    source Object which will be convert into Entity.
     * @param destinationEntity target Object.
     * @param <T>
     * @return Entity Object.
     */
    public <T> T mapDtoToEntity(Object sourceDto, Class<T> destinationEntity) {
        return modelMapper.map(sourceDto, destinationEntity);
    }

    /**
     * This method is used to convert Entity To DTO.
     *
     * @param entityObject source Object which will be convert into DTO.
     * @param dtoObject    target object.
     * @param <T>
     * @return DTO Object.
     */
    public <T> T mapEntityToDto(Object entityObject, Class<T> dtoObject) {
        return modelMapper.map(entityObject, dtoObject);
    }

    /**
     * <p>Note: outClass object must have default constructor with no arguments</p>
     *
     * @param entityList list of entities that needs to be mapped
     * @param outCLass   class of result list element
     * @param <D>        type of objects in result list
     * @param <T>        type of entity in <code>entityList</code>
     * @return list of mapped object with <code><D></code> type.
     */
    public <D, T> List<D> mapListOfEntityToListOfDto(Collection<T> entityList, Class<D> outCLass) {
        return entityList.stream()
                .map(entity -> mapEntityToDto(entity, outCLass))
                .collect(Collectors.toList());
    }

}
