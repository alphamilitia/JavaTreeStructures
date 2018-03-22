public class JenkinsMain {
    public static void main(String args[]) {
        LinkedBinaryTree<String> lbt = new LinkedBinaryTree<>();
        lbt.addRoot("+ ");
        lbt.addLeft(lbt.root(),"x ");
        lbt.addRight(lbt.root(),"x ");
        lbt.addLeft(lbt.left(lbt.root()),"2 ");
        lbt.addRight(lbt.left(lbt.root()),"- ");
        lbt.addLeft(lbt.right(lbt.left(lbt.root())),"a ");
        lbt.addRight(lbt.right(lbt.left(lbt.root())),"1 ");
        lbt.addLeft(lbt.right(lbt.root()),"3 ");
        lbt.addRight(lbt.right(lbt.root()),"b");

        lbt.printTree();
    }
}
