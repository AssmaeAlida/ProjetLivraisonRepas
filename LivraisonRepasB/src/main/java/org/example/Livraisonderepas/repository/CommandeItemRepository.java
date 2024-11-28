package org.example.Livraisonderepas.repository;

import org.example.Livraisonderepas.model.CommandeItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommandeItemRepository extends JpaRepository<CommandeItem, Long> {
}
