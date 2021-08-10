package beans;

import lombok.Builder;


@Builder
public class Client {
    private String firm_name;
    private String firm_addr;
    private String firm_town;
    private boolean firm_is_reg_vat;
    private String firm_mol;
    private String firm_vat_number;
}
