package test;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

/**
 * TestObjectNode
 *
 * @author by gatesma.
 */
public class TestObjectNode {

    public static void main(String[] args) {

        String render = "{\"name\": \"malong\"}";
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode node = (ObjectNode) mapper.valueToTree(JSONObject.parse(render));
        node.get("name");
    }

}
