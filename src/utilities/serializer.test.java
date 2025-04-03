package utilities;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SerializerTest {

	@Test
	void testDeserializeObjectWithNonExistentFile() {
	    String nonExistentFilePath = "non_existent_file.gol";
	    Object result = Serializer.deserializeObject(nonExistentFilePath);
	    assertNull(result);
	}
	
	@Test
	void testDeserializeObjectWithComplexNestedStructures() {
	    // Create a complex nested structure
	    Map<String, List<Set<Integer>>> complexObject = new HashMap<>();
	    List<Set<Integer>> nestedList = new ArrayList<>();
	    nestedList.add(new HashSet<>(Arrays.asList(1, 2, 3)));
	    nestedList.add(new HashSet<>(Arrays.asList(4, 5, 6)));
	    complexObject.put("key", nestedList);
	
	    String filePath = "complex_object.gol";
	    Serializer.serializeObject(complexObject, filePath);
	
	    Object deserializedObject = Serializer.deserializeObject(filePath);

	    assertNotNull(deserializedObject);
	    assertTrue(deserializedObject instanceof Map);
	    Map<String, List<Set<Integer>>> deserializedMap = (Map<String, List<Set<Integer>>>) deserializedObject;
	    assertEquals(complexObject, deserializedMap);
	
	    // Clean up the test file
	    new File(filePath).delete();
	}
	
	@Test
	void testDeserializeObjectWithValidFile() {
	    // Create a test object
	    String testObject = "Test String";
	    String filePath = "test_object.gol";
	
	    Serializer.serializeObject(testObject, filePath);

	    Object deserializedObject = Serializer.deserializeObject(filePath);
	
	    assertNotNull(deserializedObject);
	    assertTrue(deserializedObject instanceof String);
	    assertEquals(testObject, deserializedObject);
	
	    // Clean up the test file
	    new File(filePath).delete();
	}
}
