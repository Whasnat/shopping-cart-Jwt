package Cart.Cart.rapository;

import Cart.Cart.model.Shop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface ShopRepository extends JpaRepository<Shop, Long> {
    /*@Modifying
    @Transactional
    @Query(nativeQuery = true, value = "update shop set :qry where id = :shopId")
    int updateShop(@Param("qry") String qry, @Param("shopId") Long shopId);*/
}
