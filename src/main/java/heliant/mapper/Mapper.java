package heliant.mapper;

public interface Mapper <D, E> {
    E dtoToEntity(D dto);
    D entityToDto(E entity);
}
