import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class XMLParserExample extends JFrame {

    private JTree tree;

    public XMLParserExample() {
        setTitle("XML Parser Example");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Инициализация компонентов
        tree = new JTree();
        JScrollPane scrollPane = new JScrollPane(tree);
        add(scrollPane);

        // Загрузка и парсинг XML-файла
        parseXML("example.xml");

        // Отображение окна
        setVisible(true);
    }

    private void parseXML(String filePath) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(filePath);

            Element root = document.getDocumentElement();
            DefaultMutableTreeNode rootNode = new DefaultMutableTreeNode(root.getNodeName());
            tree.setModel(new DefaultTreeModel(rootNode));

            // Рекурсивное добавление узлов в дерево
            addNodes(root, rootNode);

        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Ошибка при загрузке и парсинге XML-файла", "Ошибка", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void addNodes(Node xmlNode, DefaultMutableTreeNode treeNode) {
        NodeList childNodes = xmlNode.getChildNodes();

        for (int i = 0; i < childNodes.getLength(); i++) {
            Node childNode = childNodes.item(i);

            if (childNode.getNodeType() == Node.ELEMENT_NODE) {
                DefaultMutableTreeNode childTreeNode = new DefaultMutableTreeNode(childNode.getNodeName());
                treeNode.add(childTreeNode);
                addNodes(childNode, childTreeNode);
            } else if (childNode.getNodeType() == Node.TEXT_NODE && !childNode.getNodeValue().trim().isEmpty()) {
                // Добавление текстовых узлов
                treeNode.add(new DefaultMutableTreeNode(childNode.getNodeValue().trim()));
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(XMLParserExample::new);
    }
}
