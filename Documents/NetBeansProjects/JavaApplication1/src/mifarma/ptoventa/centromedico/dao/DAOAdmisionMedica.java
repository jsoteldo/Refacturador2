package mifarma.ptoventa.centromedico.dao;

import java.util.List;

import mifarma.ptoventa.centromedico.domain.VtaPedidoAtencionMedica;
import mifarma.ptoventa.reference.DAOTransaccion;

public interface DAOAdmisionMedica extends DAOTransaccion{
    public List<VtaPedidoAtencionMedica> listPedidoATM(VtaPedidoAtencionMedica vtaPedidoAtencionMedica) throws Exception;
}
