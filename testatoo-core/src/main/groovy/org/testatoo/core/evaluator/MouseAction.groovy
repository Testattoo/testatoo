package org.testatoo.core.evaluator

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
public interface MouseAction {

    void click(String id)

    void doubleClick(String id)

    void rightClick(String id)

    void mouseOver(String id)

    void mouseOut(String id)

    void dragAndDrop(String originId, String targetId)


}