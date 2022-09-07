package com.ernesto.springboot.gsm.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ernesto.springboot.gsm.models.entity.ComprobanteEncabezado;

public interface IComprobanteEncabezadoDao extends JpaRepository<ComprobanteEncabezado, Long>{
	
	/*
	 * Sin especificar nada anda pero de esta forma solo hace un query en vez de:
	 * 
	 * select comprobant0_.comprobante_encabezado_id as comproba1_2_, comprobant0_.cliente_id as cliente_6_2_, comprobant0_.fecha as fecha2_2_, comprobant0_.observaciones as observac3_2_, comprobant0_.recargo_descuento as recargo_4_2_, comprobant0_.tipo_comprobante_id as tipo_com7_2_, comprobant0_.tipo_venta as tipo_ven5_2_, comprobant0_.usuario_id as usuario_8_2_ from comprobantes_encabezados comprobant0_
	 * select cliente0_.cliente_id as cliente_1_1_0_, cliente0_.apellido as apellido2_1_0_, cliente0_.cuit as cuit3_1_0_, cliente0_.domicilio_id as domicili7_1_0_, cliente0_.email as email4_1_0_, cliente0_.nombre as nombre5_1_0_, cliente0_.tefono as tefono6_1_0_, cliente0_.tipo_iva_id as tipo_iva8_1_0_, domicilio1_.domicilio_id as domicili1_4_1_, domicilio1_.barrio as barrio2_4_1_, domicilio1_.calle as calle3_4_1_, domicilio1_.casa as casa4_4_1_, domicilio1_.departamento as departam5_4_1_, domicilio1_.localidad as localida6_4_1_, domicilio1_.manzana as manzana7_4_1_, domicilio1_.numero as numero8_4_1_, domicilio1_.piso as piso9_4_1_, domicilio1_.provincia as provinc10_4_1_, tipoiva2_.tipo_iva_id as tipo_iva1_10_2_, tipoiva2_.codigo_afip as codigo_a2_10_2_, tipoiva2_.descripcion as descripc3_10_2_, tipoiva2_.visible as visible4_10_2_ from clientes cliente0_ left outer join domicilios domicilio1_ on cliente0_.domicilio_id=domicilio1_.domicilio_id left outer join tipos_iva tipoiva2_ on cliente0_.tipo_iva_id=tipoiva2_.tipo_iva_id where cliente0_.cliente_id=?
	 * select tipocompro0_.tipo_comprobante_id as tipo_com1_9_0_, tipocompro0_.codigo_afip as codigo_a2_9_0_, tipocompro0_.descripcion as descripc3_9_0_, tipocompro0_.visible as visible4_9_0_ from tipos_comprobantes tipocompro0_ where tipocompro0_.tipo_comprobante_id=?
	 * select usuario0_.usuario_id as usuario_1_11_0_, usuario0_.activo as activo2_11_0_, usuario0_.apellido as apellido3_11_0_, usuario0_.email as email4_11_0_, usuario0_.imagen as imagen5_11_0_, usuario0_.nombre as nombre6_11_0_, usuario0_.password as password7_11_0_ from usuarios usuario0_ where usuario0_.usuario_id=?
	 * select items0_.comprobante_encabezado_id as comproba6_3_0_, items0_.comprobante_item_id as comproba1_3_0_, items0_.comprobante_item_id as comproba1_3_1_, items0_.articulo_id as articulo5_3_1_, items0_.cantidad as cantidad2_3_1_, items0_.precio as precio3_3_1_, items0_.recago_descuento as recago_d4_3_1_ from comprobantes_items items0_ where items0_.comprobante_encabezado_id=?
	 * select articulo0_.articulo_id as articulo1_0_0_, articulo0_.codigo_barra as codigo_b2_0_0_, articulo0_.codigo_fabrica as codigo_f3_0_0_, articulo0_.descripcion as descripc4_0_0_, articulo0_.imagen as imagen5_0_0_, articulo0_.margen as margen6_0_0_, articulo0_.precio_lista as precio_l7_0_0_, articulo0_.proveedor_id as proveed10_0_0_, articulo0_.punto_reposicion as punto_re8_0_0_, articulo0_.rubro_id as rubro_i11_0_0_, articulo0_.stock_actual as stock_ac9_0_0_, proveedor1_.proveedor_id as proveedo1_6_1_, proveedor1_.descripcion as descripc2_6_1_, proveedor1_.email as email3_6_1_, proveedor1_.pagina_web as pagina_w4_6_1_, proveedor1_.telefono as telefono5_6_1_, rubro2_.rubro_id as rubro_id1_8_2_, rubro2_.descripcion as descripc2_8_2_ from articulos articulo0_ left outer join proveedores proveedor1_ on articulo0_.proveedor_id=proveedor1_.proveedor_id left outer join rubros rubro2_ on articulo0_.rubro_id=rubro2_.rubro_id where articulo0_.articulo_id=?
	 * select articulo0_.articulo_id as articulo1_0_0_, articulo0_.codigo_barra as codigo_b2_0_0_, articulo0_.codigo_fabrica as codigo_f3_0_0_, articulo0_.descripcion as descripc4_0_0_, articulo0_.imagen as imagen5_0_0_, articulo0_.margen as margen6_0_0_, articulo0_.precio_lista as precio_l7_0_0_, articulo0_.proveedor_id as proveed10_0_0_, articulo0_.punto_reposicion as punto_re8_0_0_, articulo0_.rubro_id as rubro_i11_0_0_, articulo0_.stock_actual as stock_ac9_0_0_, proveedor1_.proveedor_id as proveedo1_6_1_, proveedor1_.descripcion as descripc2_6_1_, proveedor1_.email as email3_6_1_, proveedor1_.pagina_web as pagina_w4_6_1_, proveedor1_.telefono as telefono5_6_1_, rubro2_.rubro_id as rubro_id1_8_2_, rubro2_.descripcion as descripc2_8_2_ from articulos articulo0_ left outer join proveedores proveedor1_ on articulo0_.proveedor_id=proveedor1_.proveedor_id left outer join rubros rubro2_ on articulo0_.rubro_id=rubro2_.rubro_id where articulo0_.articulo_id=?
	 * 
	 * el join fetch permite reducir la cantidad de selects
	 * 
	 * Sin el distinct recupera el mismo comprobante tantas veces como items tenga 
	 * ver:	.setHint("hibernate.query.passDistinctThrough", false)
	 * 		.setHint(QueryHints.HINT_PASS_DISTINCT_THROUGH, false)
	 * 
	 * https://in.relation.to/2016/08/04/introducing-distinct-pass-through-query-hint/
	 * https://vladmihalcea.com/jpql-distinct-jpa-hibernate/
	 * 
	 */
	
	/*
	 * @Query("select ce from EncabezadoComprobante ce join fetch ce.cliente c join fetch ce.ComprobanteItem ci join fetch ci.articulo where f.id = ?1")
	 * public Factura fetchByIdWithClienteWithItemFacturaWithProducto(Long id);
	 */
	
	@Query("select distinct ce "
		+ "from ComprobanteEncabezado ce "
		+ "join fetch ce.cliente c join fetch c.domicilio join fetch c.tipoIva "
		+ "join fetch ce.tipoComprobante tc "
		+ "join fetch ce.usuario u "
		+ "join fetch ce.items ci join fetch ci.articulo a join fetch a.proveedor join fetch a.rubro ")
	public List<ComprobanteEncabezado> fetchAllWithClienteWithTipoComprobanteWithItemWithUSuarioWithArticulo();


	
}
