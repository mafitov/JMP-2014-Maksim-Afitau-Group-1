package by.mentoring.service;


import by.mentoring.model.Person;
import com.google.common.collect.Lists;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.type.TypeFactory;
import org.codehaus.jackson.type.JavaType;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.*;

@RunWith(PowerMockRunner.class)
@PrepareForTest(TypeFactory.class)
public class PersonServiceTest {

    private static final int ID = 1;

    private TypeFactory factory;
    @Mock protected File storageFile;
    @Mock protected ObjectMapper mapper;

    @InjectMocks private PersonService service;

    @Before public void init() throws IOException {

        Person person = new Person();
        person.setId(ID);

        MockitoAnnotations.initMocks(this);

        factory = PowerMockito.mock(TypeFactory.class);
        when(mapper.getTypeFactory()).thenReturn(factory);
        when(factory.constructParametricType(List.class, Person.class)).thenCallRealMethod();
        when(mapper.readValue(eq(storageFile), any(JavaType.class))).thenReturn(Lists.newArrayList(person));
    }

    @Test public void testSaveOrUpdate_whenSave() throws IOException {
        Person person = new Person();
        person.setId(ID);
        Person p = service.saveOrUpdate(person);

        assertEquals(ID + 1, p.getId().intValue());
    }

    @Test public void testGet() {
        Person p = service.get(ID);

        assertEquals(p.getId().intValue(), ID);
    }

    @Test public void testGetAll() throws IOException {
        List list = service.getAll();

        verify(mapper, times(1)).readValue(eq(storageFile), any(JavaType.class));
        assertEquals(list.size(), 1);
    }

}
