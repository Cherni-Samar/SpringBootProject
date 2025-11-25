package tn.esprit.firstdemo.mapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import tn.esprit.firstdemo.dto.ChambreDTO;
import tn.esprit.firstdemo.entity.Chambre;


// Déclare comme interface
@Mapper(componentModel = "spring", uses = {BlocMapper.class})
public interface ChambreMapper {
    // Instance pour utilisation statique si besoin
    ChambreMapper INSTANCE = Mappers.getMapper(ChambreMapper.class);

    // Mapping automatique entity -> DTO
    ChambreDTO toDto(Chambre chambre);

    // Mapping automatique DTO -> entity
    Chambre toEntity(ChambreDTO chambreDTO);
}
