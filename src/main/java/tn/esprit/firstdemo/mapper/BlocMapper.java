package tn.esprit.firstdemo.mapper;


import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import tn.esprit.firstdemo.dto.BlocDTO;
import tn.esprit.firstdemo.entity.Bloc;


@Mapper(componentModel = "spring")
public interface BlocMapper {

    BlocMapper INSTANCE = Mappers.getMapper(BlocMapper.class);

    BlocDTO toDto(Bloc bloc);

    Bloc toEntity(BlocDTO blocDTO);
}
