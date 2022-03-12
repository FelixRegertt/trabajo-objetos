package com.example.endpoints;

import com.example.endpoints.utils.FabricaCriterio;
import com.example.endpoints.utils.Utils;
import com.example.exceptions.ExcepcionServicio;
import com.example.modelo.Archivo;
import com.example.modelo.Elemento;
import com.example.modelo.criterios.Criterio;
import com.example.servicios.Servicios;
import com.google.gson.Gson;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

@WebServlet(name="ArchivosConFiltro", value=Utils.URL_FILTRO_ARCHIVOS)
public class ArchivosFiltro extends HttpServlet {

    private final Gson gson = new Gson();
    private final Servicios servicio = Servicios.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        Map<String, String> filtros = Utils.getCriterios(request);
        String carpetaBase = request.getParameter("carpetaBase");
        FabricaCriterio fabrica = new FabricaCriterio();
        Criterio c = fabrica.getCriterio(filtros);

        try {
            if(servicio.existeElemento(carpetaBase)) {
                Elemento directorio = servicio.getDirectorio(carpetaBase);
                List<Archivo> archivos = directorio.filtrar(c);
                String archivosJson = this.gson.toJson(archivos);
                out.print(archivosJson);
            }else{
                response.setStatus(Utils.NOT_FOUND);
                out.print("El directorio no existe!");
            }
        } catch (ExcepcionServicio e) {
            response.setStatus(Utils.INTERNAL_SERVER_ERROR);
            out.print("Error al filtrar los archivos");
        }finally {
            out.flush();
        }
    }

}
