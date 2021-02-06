import java.util.Comparator;

class ComparatorBlockChain implements Comparator<BlockChain> {


    @Override
    public int compare(BlockChain o1, BlockChain o2) {
        return o1.verifiedSize()-o2.verifiedSize();
    }
}
