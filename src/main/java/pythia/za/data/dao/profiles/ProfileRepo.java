package pythia.za.data.dao.profiles;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface ProfileRepo extends JpaRepository<Profile, String > {
}
