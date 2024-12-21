import java.util.Scanner;
public class SubnettingExample {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // Input: IP Address and CIDR notation
        System.out.print("Enter IP address (e.g., 192.168.1.0): ");
        String ipAddress = scanner.nextLine();
        System.out.print("Enter CIDR notation (e.g., /24): ");
        int cidr = scanner.nextInt();
        // Calculate number of subnets and hosts per subnet
        int totalBits = 32;  // IPv4 has 32 bits
        int networkBits = cidr;  // Network bits are given by CIDR
        int hostBits = totalBits - networkBits;  // Remaining bits are for hosts
// Hosts per subnet: 2^hostBits - 2 (for network and broadcast addresses)
        int hostsPerSubnet = (int) Math.pow(2, hostBits) - 2;
        System.out.println("Hosts per subnet: " + hostsPerSubnet);
        // Number of subnets depends on how many bits are used for subnetting
        // Subnets are not directly calculated here, but for larger subnetting scenarios,
        // it's calculated based on how many bits are borrowed for subnets.
        // Subnet Mask Calculation
        String subnetMask = calculateSubnetMask(cidr);
        System.out.println("Subnet Mask: " + subnetMask);
        // Calculate first and last valid host address in the subnet
        String firstHost = calculateFirstHost(ipAddress, cidr);
        String lastHost = calculateLastHost(ipAddress, cidr);
        System.out.println("First Host: " + firstHost);
        System.out.println("Last Host: " + lastHost);
        scanner.close();
    }
// Function to calculate subnet mask from CIDR
    private static String calculateSubnetMask(int cidr) {
        int mask = 0xFFFFFFFF << (32 - cidr);  // Shift bits to form the mask
        return String.format("%d.%d.%d.%d",
                (mask >> 24) & 0xFF,
                (mask >> 16) & 0xFF,
                (mask >> 8) & 0xFF,
                mask & 0xFF);
    }
    // Function to calculate first host address
    private static String calculateFirstHost(String ipAddress, int cidr) {
        String[] octets = ipAddress.split("\\.");
        int[] ip = new int[4];
        // Convert IP address to integers
        for (int i = 0; i < 4; i++) {
            ip[i] = Integer.parseInt(octets[i]);
        }
// Find the first host by adding 1 to the network address
        int maskBits = 32 - cidr;
        int hostBits = (1 << maskBits) - 2;  // Hosts = 2^remaining bits - 2

        ip[3] = (ip[3] & (0xFF << maskBits)) + 1;  // First host address

        return String.format("%d.%d.%d.%d", ip[0], ip[1], ip[2], ip[3]);
    }
    // Function to calculate last host address
    private static String calculateLastHost(String ipAddress, int cidr) {
        String[] octets = ipAddress.split("\\.");
        int[] ip = new int[4];
        // Convert IP address to integers
        for (int i = 0; i < 4; i++) {
            ip[i] = Integer.parseInt(octets[i]);
        }
 // Find the last host by adding the range to the network address
        int maskBits = 32 - cidr;
        int hostBits = (1 << maskBits) - 2;  // Hosts = 2^remaining bits - 2

        ip[3] = (ip[3] & (0xFF << maskBits)) + hostBits;  // Last host address

        return String.format("%d.%d.%d.%d", ip[0], ip[1], ip[2], ip[3]);
    }
}

