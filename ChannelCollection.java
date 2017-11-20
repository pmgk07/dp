public interface ChannelCollection extends Exportable{

	public void addChannel(Channel c);
	
	public void removeChannel(Channel c);
	
	public ChannelIterator iterator(ChannelTypeEnum type);

	public void export(String path);
	
}
