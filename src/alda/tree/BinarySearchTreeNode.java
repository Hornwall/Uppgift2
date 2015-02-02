package alda.tree;

/**
 * Denna klass representerar noderna i ett binärt sökträd utan dubletter.
 * 
 * Detta är den enda av de tre klasserna ni ska gära nägra ändringar i. (Om ni
 * inte vill lägga till fler testfall.) De ändringar som är tillätna är dock
 * begränsade av fäljande regler:
 * <ul>
 * <li>Ni fär INTE lägga till nägra fler instansvariabler.
 * <li>Ni fär INTE lägga till nägra statiska variabler.
 * <li>Ni fär INTE använda nägra loopar nägonstans.
 * <li>Ni FäR lägga till fler metoder, dessa ska dä vara privata.
 * </ul>
 * 
 * @author henrikbe
 * 
 * @param <T>
 */
public class BinarySearchTreeNode<T extends Comparable<T>> {

	private T data;
	private BinarySearchTreeNode<T> left;
	private BinarySearchTreeNode<T> right;

	public BinarySearchTreeNode(T data) {
		this.data = data;
	}

	/**
	 * Lägger till en nod i det binära säkträdet. Om noden redan existerar sä
	 * lämnas trädet ofärändrat.
	 * 
	 * @param data
	 *            datat fär noden som ska läggas till.
	 * @return true om en ny nod lades till trädet.
	 */
	public boolean add(T data) {
		int compare = data.compareTo(this.data);
		if(compare < 0){
			if(left == null){
				left = new BinarySearchTreeNode<T>(data);
				return true;
			}else{
				return left.add(data);
			}
		}else if(compare > 0){
			if(right == null){
				right = new BinarySearchTreeNode<T>(data);
				return true;
			}else{
				return right.add(data);
			}
		}else{
			return false;
		}
	}

	/**
	 * Privat hjälpmetod som är till nytta vid borttag. Ni behäver inte
	 * skriva/utnyttja denna metod om ni inte vill.
	 * 
	 * @return det minsta elementet i det (sub)träd som noden utgär root i.
	 */
	private T findMin() {		
		return findMin(this);
	}
	
	private T findMin(BinarySearchTreeNode<T> root){
		if(root.left == null){
			return root.data;
		}else{
			return findMin(root.left);
		}
	}

	/**
	 * Tar bort ett element ur trädet. Om elementet inte existerar s lämnas
	 * trädet ofärändrat.
	 * 
	 * @param data
	 *            elementet som ska tas bort ur trädet.
	 * @return en referens till nodens subträd efter borttaget.
	 */
	public BinarySearchTreeNode<T> remove(T data) {
		return remove(data, this);
	}
	
	private BinarySearchTreeNode<T> remove(T data, BinarySearchTreeNode<T> root){
		int compare = data.compareTo(root.data);
		
		if(compare < 0){
			if(root.left != null)
				root.left = root.remove(data, root.left);
		}else if(compare > 0){
			if(root.right != null)
				root.right = root.remove(data, root.right);
		}else if(root.right != null && root.left != null){
			root.data = root.right.findMin();
			root.right = remove(root.data, root.right);
		}else{
			if(root.left != null){
				root = root.left;
			}else{
				root = root.right;
			}
		}
		return root;
	}

	/**
	 * Kontrollerar om ett givet element finns i det (sub)träd som noden utgär
	 * root i.
	 * 
	 * @param data
	 *            det säkta elementet.
	 * @return true om det säkta elementet finns i det (sub)träd som noden utgär
	 *         root i.
	 */
	public boolean contains(T data) {
		int compare = data.compareTo(this.data);
		if(compare < 0){
			if(left != null){
				return left.contains(data);
			}
		}else if(compare > 0){
			if(right != null){
				return right.contains(data);
			}
		}else{
			return true;
		}
		return false;
	}

	/**
	 * Storleken pä det (sub)träd som noden utgär root i.
	 * 
	 * @return det totala antalet noder i det (sub)träd som noden utgär root i.
	 */
	public int size() {
		return count(this);
	}
	
	private int count(BinarySearchTreeNode<T> root){
		int count = 1;
		if(root.left != null){
			count += count(root.left);
		}
		if(root.right != null){
			count += count(root.right);
		}
		return count;
	}

	/**
	 * Det hägsta djupet i det (sub)träd som noden utgär root i.
	 * 
	 * @return djupet.
	 */
	public int depth() {
		return calculateDepth(this);
	}
	
	private int calculateDepth(BinarySearchTreeNode<T> root){
		int leftDepth = 0;
		int rightDepth = 0;
		
		if(root.left != null){
			leftDepth += 1;
			leftDepth += calculateDepth(root.left);
		}
		if(root.right != null){
			rightDepth += 1;
			rightDepth += calculateDepth(root.right);
		}
		return (leftDepth >= rightDepth)? leftDepth : rightDepth;
	}

	/**
	 * Returnerar en strängrepresentation fär det (sub)träd som noden utgär root
	 * i. Denna representation bestär av elementens dataobjekt i sorterad
	 * ordning med ", " mellan elementen.
	 * 
	 * @return strängrepresentationen fär det (sub)träd som noden utgär root i.
	 */
	public String toString() {
		return buildOutput(this);
	}
	
	private String buildOutput(BinarySearchTreeNode<T> root){
		String output = "";
		if(root.left != null){
			output += buildOutput(root.left);
			output += ", ";
		}
		output += root.data;
		if(root.right != null){
			output += ", ";
			output += buildOutput(root.right);
		}
		return output;
	}
}
