package org.af.jhlir.call;

import java.util.List;

/**
 * Interface for an S3 Object.
 * @param <WRAPPED_TYPE> underlying type of the used back-end, @see RObj
 */
public interface S3Obj<WRAPPED_TYPE> extends RObj<WRAPPED_TYPE>{
    /**
     * Get all classes of the S3 class attribute.
     * @return all classes of the S3 class attribute.
     */
    String[] getS3Classes();

    /**
     * Get all classes of the S3 class attribute as a List of Strings.
     * @return all classes of the S3 class attribute.
     */
    List<String> getS3ClassesAsList();

    /**
     * Get first class of the class attribute. There's no guarantee that this is the class you want if multiple S3
     * classes are present, though in most cases this will be the most relevant one.
     * @return first class of the class attribute.
     */
    String getS3Class();
}
