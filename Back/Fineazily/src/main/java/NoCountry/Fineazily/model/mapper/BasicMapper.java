package NoCountry.Fineazily.model.mapper;


import org.mapstruct.MappingTarget;

public interface BasicMapper<Entity, Dto> {
    Entity toEntity(Dto dto);

    Dto toDto(Entity entity);

    void updateEntity(Dto dto, @MappingTarget Entity existingEntity);
}
