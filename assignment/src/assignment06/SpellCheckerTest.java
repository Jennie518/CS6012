package assignment06;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.Arrays;

public class SpellCheckerTest {

    private SpellChecker spellChecker;

    @BeforeEach
    public void setUp(@TempDir Path tempDir) throws IOException {
        // 创建临时字典文件
        File dictionaryFile = tempDir.resolve("dictionary.txt").toFile();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(dictionaryFile))) {
            writer.write("hello\nworld\ngood\nluck");
        }
        // 实例化SpellChecker
        spellChecker = new SpellChecker(dictionaryFile);
    }

    @Test
    public void testSpellCheck_noMisspellings() throws IOException {
        // 创建临时测试文件
        File testFile = new File("testFile.txt");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(testFile))) {
            writer.write("hello world good luck");
        }
        // 执行拼写检查
        List<String> misspelledWords = spellChecker.spellCheck(testFile);
        // 断言没有拼写错误
        assertTrue(misspelledWords.isEmpty());
    }

    @Test
    public void testSpellCheck_withMisspellings() throws IOException {
        // 创建临时测试文件
        File testFile = new File("testFile.txt");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(testFile))) {
            writer.write("helo wrld gud luk");
        }
        // 执行拼写检查
        List<String> misspelledWords = spellChecker.spellCheck(testFile);
        // 断言所有单词都是拼写错误
        List<String> expectedMisspellings = Arrays.asList("helo", "wrld", "gud", "luk");
        assertTrue(misspelledWords.containsAll(expectedMisspellings) && misspelledWords.size() == expectedMisspellings.size());
    }

    @Test
    public void testAddToDictionary() {
        // 添加新词到字典
        spellChecker.addToDictionary("newword");
        // 检查新词是否已添加
        assertTrue(spellChecker.dictionary.contains("newword"));
    }

    @Test
    public void testRemoveFromDictionary() {
        // 首先添加词到字典，然后尝试移除
        spellChecker.addToDictionary("removeWord");
        spellChecker.removeFromDictionary("removeWord");
        // 检查词是否被移除
        assertFalse(spellChecker.dictionary.contains("removeWord"));
    }
}
