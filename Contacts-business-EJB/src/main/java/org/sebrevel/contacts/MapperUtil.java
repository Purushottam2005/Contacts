package org.sebrevel.contacts;

import org.dozer.Mapper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Sébastien Revel
 * Date: 11/06/12
 * Time: 15:33
 * To change this template use File | Settings | File Templates.
 */
public class MapperUtil {


    // TODO doc !!
    public static <U, D, I> List<I> map(Mapper mapper, List<U> source, Class<D> destClass, Class<I> interf) {

        final List<I> dest = new ArrayList();

        for (U element : source) {
            I result = (I) mapper.map(element, destClass);
            dest.add(result);
        }

        return dest;
    }
}
