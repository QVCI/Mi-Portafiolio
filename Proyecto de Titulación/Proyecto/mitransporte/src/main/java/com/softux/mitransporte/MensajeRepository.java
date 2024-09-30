package com.softux.mitransporte;

import java.time.LocalTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MensajeRepository extends JpaRepository<Mensaje, Long>
{
    List<Mensaje> findByHoraPublicacionBefore(LocalTime hora);
}