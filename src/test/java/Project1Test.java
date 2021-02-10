import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer.MethodName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

/**
 * A test suite for project 1. During development, run individual tests instead
 * of this entire test suite!
 *
 * @author CS 212 Software Development
 * @author University of San Francisco
 * @version Spring 2021
 */
@TestMethodOrder(MethodName.class)
public class Project1Test extends TestUtilities {
	
	/*
	 * NOTE: Avoid running this entire test suite! 
	 * 
	 * The project tests will always take awhile to run. The remote test will only
	 * run the tests with the "verify" tag. The other tests are provided to help
	 * you debug only. Run each test individually in the order they are provided
	 * in this file while you are developing!
	 * 
	 * Utilize the "Outline" view in Eclipse to navigate this file quickly.
	 */

	/**
	 * Tests the output of this project.
	 */
	@Nested
	@TestMethodOrder(OrderAnnotation.class)
	public class A_OutputTest {
		/**
		 * Tests the index output for files in the simple subdirectory.
		 *
		 * @param filename filename of a text file in the simple subdirectory
		 */
		@Order(1)
		@ParameterizedTest
		@ValueSource(strings = {
				"hello.txt",
				"animals.text",
				"capitals.txt",
				"capital_extension.TXT",
				"digits.tXt",
				"position.teXt",
				"empty.txt",
				"symbols.txt",
				"words.tExT"
		})
		public void testSimpleTextFiles(String filename) {
			Path input = TEXT_PATH.resolve("simple").resolve(filename);
			testIndex("index-simple", input);
		}
		
		/**
		 * Tests the index output for files in the simple subdirectory.
		 *
		 * @param filename filename of a non-text file in the simple subdirectory
		 */
		@Order(2)
		@ParameterizedTest
		@ValueSource(strings = {
				"no_extension",
				"sentences.md",
				"double_extension.txt.html",
				"wrong_extension.html"
		})
		public void testSimpleOtherFiles(String filename) {
			Path input = TEXT_PATH.resolve("simple").resolve(filename);
			testIndex("index-simple", input);
		}
		
		/**
		 * Tests the index output for files in the stems subdirectory.
		 *
		 * @param filename filename of a non-text file in the simple subdirectory
		 */
		@Order(3)
		@ParameterizedTest
		@ValueSource(strings = {
				"stem-in.txt",
				"stem-out.txt"
		})
		public void testStemsFiles(String filename) {
			Path input = TEXT_PATH.resolve("stems").resolve(filename);
			testIndex("index-stems", input);
		}
		
		/**
		 * Tests the index output for files in the rfcs subdirectory.
		 *
		 * @param filename filename of a text file in the rfcs subdirectory
		 */
		@Order(4)
		@ParameterizedTest
		@ValueSource(strings = {
				"rfc3629.txt", // UTF-8, a transformation format of ISO 10646
				"rfc475.txt",  // FTP AND NETWORK MAIL SYSTEM
				"rfc5646.txt", // Tags for Identifying Languages
				"rfc6805.txt", // PCE Hierarchy Framework
				"rfc6838.txt", // Media Type Specifications and Registration Procedures
				"rfc7231.txt"  // Hypertext Transfer Protocol (HTTP/1.1): Semantics and Content
		})
		public void testRfcFiles(String filename) {
			Path input = TEXT_PATH.resolve("rfcs").resolve(filename);
			testIndex("index-rfcs", input);
		}
		
		/**
		 * Tests the index output for files in the guten subdirectory.
		 *
		 * @param filename filename of a text file in the guten subdirectory
		 */
		@Order(5)
		@ParameterizedTest
		@ValueSource(strings = {
				"pg37134.txt", // The Elements of Style by William Strunk
				"pg22577.txt", // Practical Grammar and Composition by Thomas Wood
				"pg1661.txt",  // Adventures of Sherlock Holmes by Arthur Conan Doyle
				"pg1322.txt",  // Leaves of Grass by Walt Whitman
				"50468-0.txt", // ALGOL Compiler by L. L. Bumgarner
				"2701-0.txt",  // Moby Dick by Herman Melville
				"1400-0.txt"   // Great Expectations by Charles Dickens
		})
		public void testGutenFiles(String filename) {
			Path input = TEXT_PATH.resolve("guten").resolve(filename);
			testIndex("index-guten", input);
		}
		
		/**
		 * Tests the index output for the simple subdirectory.
		 */
		@Order(6)
		@Test
		public void testSimpleDirectory() {
			Path input = TEXT_PATH.resolve("simple");
			testIndex("index-simple", input);
		}
		
		/**
		 * Tests the index output for the stems subdirectory.
		 */
		@Order(7)
		@Test
		public void testStemsDirectory() {
			Path input = TEXT_PATH.resolve("stems");
			testIndex("index-stems", input);
		}

		/**
		 * Tests the index output for the rfcs subdirectory.
		 */
		@Order(8)
		@Test
		public void testRfcDirectory() {
			Path input = TEXT_PATH.resolve("rfcs");
			testIndex("index-rfcs", input);
		}

		/**
		 * Tests the index output for the guten subdirectory.
		 */
		@Order(9)
		@Test
		public void testGutenDirectory() {
			Path input = TEXT_PATH.resolve("guten");
			testIndex("index-guten", input);
		}

		/**
		 * Tests the index output for all of the text files.
		 */
		@Order(10)
		@Test
		@Tag("verify")
		public void testText() {
			Path input = TEXT_PATH;
			testIndex(".", input);
		}
	}

	/**
	 * Tests the exception handling of this project.
	 */
	@Nested
	@TestMethodOrder(OrderAnnotation.class)
	@Tag("verify")
	public class B_ExceptionsTest {
		/**
		 * Tests no exceptions are thrown with no arguments.
		 */
		@Order(1)
		@Test
		public void testNoArguments() {
			String[] args = {};
			testNoExceptions(args, SHORT_TIMEOUT);
		}

		/**
		 * Tests no exceptions are thrown with invalid arguments.
		 */
		@Order(2)
		@Test
		public void testBadArguments() {
			String[] args = { "hello", "world" };
			testNoExceptions(args, SHORT_TIMEOUT);
		}

		/**
		 * Tests no exceptions are thrown with a missing path value.
		 */
		@Order(3)
		@Test
		public void testMissingPath() {
			String[] args = { "-text" };
			testNoExceptions(args, SHORT_TIMEOUT);
		}

		/**
		 * Tests no exceptions are thrown with an invalid path value.
		 */
		@Order(4)
		@Test
		public void testInvalidPath() {
			// generates a random path name
			String path = Long.toHexString(Double.doubleToLongBits(Math.random()));
			String[] args = { TEXT_FLAG, path };
			testNoExceptions(args, SHORT_TIMEOUT);
		}

		/**
		 * Tests no exceptions are thrown with no index output.
		 *
		 * @throws IOException if IO error occurs
		 */
		@Order(5)
		@Test
		public void testNoOutput() throws IOException {
			String[] args = { TEXT_FLAG, HELLO };

			// make sure to delete old output file if it exists
			Files.deleteIfExists(INDEX_DEFAULT);

			// make sure a new index.json was not created
			testNoExceptions(args, SHORT_TIMEOUT);
			Assertions.assertFalse(Files.exists(INDEX_DEFAULT));
		}

		/**
		 * Tests no exceptions are thrown with a default output value.
		 *
		 * @throws IOException if IO error occurs
		 */
		@Order(6)
		@Test
		public void testDefaultOutput() throws IOException {
			String[] args = { TEXT_FLAG, HELLO, INDEX_FLAG };
			
			// make sure to delete old index.json if it exists
			Files.deleteIfExists(INDEX_DEFAULT);

			// make sure a new index.json was created
			testNoExceptions(args, SHORT_TIMEOUT);
			Assertions.assertTrue(Files.exists(INDEX_DEFAULT));
		}

		/**
		 * Tests no exceptions are thrown with only output (no input path).
		 *
		 * @throws IOException if IO error occurs
		 */
		@Order(7)
		@Test
		public void testEmptyOutput() throws IOException {
			String[] args = { INDEX_FLAG };

			// make sure to delete old index.json if it exists
			Files.deleteIfExists(INDEX_DEFAULT);

			// make sure a new index.json was created
			testNoExceptions(args, SHORT_TIMEOUT);
			Assertions.assertTrue(Files.exists(INDEX_DEFAULT));
		}

		/**
		 * Tests no exceptions are thrown with arguments in a different order.
		 *
		 * @throws IOException if IO error occurs
		 */
		@Order(8)
		@Test
		public void testSwitchedOrder() throws IOException {
			String[] args = { INDEX_FLAG, TEXT_FLAG, HELLO };

			// make sure to delete old index.json if it exists
			Files.deleteIfExists(INDEX_DEFAULT);

			// make sure a new index.json was created
			testNoExceptions(args, SHORT_TIMEOUT);
			Assertions.assertTrue(Files.exists(INDEX_DEFAULT));
		}
	}
	
	/*
	 * Members and methods to help configure these tests.
	 */

	/** Path to the hello.txt file used for testing. */
	public static final String HELLO = TEXT_PATH.resolve("simple").resolve("hello.txt").toString();

	/**
	 * Generates the arguments to use for the output test cases. Designed to be used
	 * inside a JUnit test.
	 *
	 * @param subdir the output subdirectory to use
	 * @param input the input path to use
	 */
	public static void testIndex(String subdir, Path input) {
		String filename = outputFileName("index", input);

		Path actual = ACTUAL_PATH.resolve(filename);
		Path expected = EXPECTED_PATH.resolve("index").resolve(subdir).resolve(filename);

		String[] args = {
				TEXT_FLAG, input.normalize().toString(),
				INDEX_FLAG, actual.normalize().toString()
		};

		Assertions.assertTimeoutPreemptively(LONG_TIMEOUT, () -> {
			checkOutput(args, actual, expected);
		});
	}

}
