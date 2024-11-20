package de.atruvia.webapp.presentation.mapper;


import de.atruvia.webapp.presentation.dto.SchweinDTO;
import de.atruvia.webapp.service.model.Schwein;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SchweinDTOMapper {
    SchweinDTO convert(Schwein schwein);
    Schwein convert(SchweinDTO schweinDto);

    Iterable<SchweinDTO> convert(Iterable<Schwein> schweine);
}
