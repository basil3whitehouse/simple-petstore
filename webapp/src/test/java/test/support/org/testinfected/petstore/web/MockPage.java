package test.support.org.testinfected.petstore.web;

import org.hamcrest.Matcher;
import org.testinfected.molecule.Response;
import org.testinfected.petstore.Page;
import org.testinfected.petstore.util.Context;

import java.io.IOException;
import java.util.Map;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.sameInstance;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasEntry;

public class MockPage implements Page {

    private Response response;
    private Map<String, Object> context;

    public void render(Response response, Context context) throws IOException {
        this.response = response;
        this.context = context.asMap();
    }

    public void assertRenderedTo(Response to) {
        assertThat("rendered to", this.response, sameInstance(to));
    }

    public <K, V> void assertRenderedWith(K key, V value) {
        assertRenderedWith(equalTo(key), equalTo(value));
    }

    @SuppressWarnings("unchecked")
    public <K, V> void assertRenderedWith(Matcher<? super K> keyMatcher, Matcher<? super V> valueMatcher) {
        assertRenderingContext(hasEntry(keyMatcher, valueMatcher));
    }

    @SuppressWarnings("unchecked")
    public void assertRenderingContext(Matcher<?> contextMatcher) {
        assertThat("rendering context", context, (Matcher<Object>) contextMatcher);
    }
}
