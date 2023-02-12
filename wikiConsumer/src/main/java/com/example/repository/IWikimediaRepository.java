package com.example.repository;

import com.example.entity.Media;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IWikimediaRepository extends JpaRepository<Media, Long> {

}
