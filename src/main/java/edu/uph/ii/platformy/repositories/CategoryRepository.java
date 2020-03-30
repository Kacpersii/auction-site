package edu.uph.ii.platformy.repositories;

        import edu.uph.ii.platformy.models.Category;

        import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
