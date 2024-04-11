package mx.com.ironbit.msoempleado.service;

import org.springframework.stereotype.Service;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mx.com.ironbit.msoempleado.dao.BitacoraDAO;
import mx.com.ironbit.msoempleado.entity.BitacoraEntity;
import mx.com.ironbit.msoempleado.util.model.TrazaModel;

@Slf4j
@Service
@RequiredArgsConstructor
public class BitacoraServiceImpl implements BitacoraService {

	private final BitacoraDAO bitacoraDAO;

	@Override
	public Object saveTransaccion(TrazaModel tm) {

		log.info("Entra a guardar la transaccion");
		Gson gson = new Gson();
		BitacoraEntity be = new BitacoraEntity();
		be.setIpOrigen(tm.getIpOrigen());
		be.setFechaRequest(tm.getFechaTransaccion());
		be.setMetodoEjecucion(tm.getMetodo());
		be.setTrazaJson(gson.toJson(tm));
		be = this.bitacoraDAO.saveAndFlush(be);

		return be;
	}

}
