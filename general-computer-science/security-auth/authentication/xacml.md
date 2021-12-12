## WHAT IS XACML

- Extensible Access Control Markup Language (XACML) is a vendor neutral declarative access control policy language. 
- It was created to promote common access control terminology and interoperability. 

- XACML policies are split into a hierarchy of three levels - 
  - `<PolicySet>`
    -  is a collection of `<Policy>` elements each of which contain one or more `<Rule>` elements.
  - `<Policy>` 
    - The overall `<Policy>` result is defined by the overall result of all `<Rule>` elements processed in turn. Separate `<Policy>` results are then evaluated against each other using combining algorithms define which `<Policy>` wins in case of conflict.
  - `<Rule>`
    - Each `<Rule>` within a `<Policy>` is evaluated as to whether it should grant access to a resource

**Highlights**

XACML:

1. is **XML**: you can actually read and write XACML with your favorite text editor (not that I would recommend writing XACML that way).
2. is **human-readable** and verbose enough for users to get an understanding of what it’s doing
3. belongs to the OASIS family of standards. You can download the latest standard material [here](http://docs.oasis-open.org/xacml/2.0/access_control-xacml-2.0-core-spec-os.pdf).
4. is **eXtensible**: you can add profiles to cater for specific scenarios e.g. a profile for hierarchical resources, for role-based access control, for export control…
5. is about **access control**: authorizing who can do what when and how
6. implements **ABAC**, attribute-based access control

**What’s ABAC?**

- ABAC stands for attribute-based access control. It is a natural evolution from role-based access control which itself is a natural evolution from access control lists.

**Access Control History in a nutshell**

- Once upon a time there were *access control lists*. Once a user authenticated, its identity was known and could be used in such lists. Think of clubs and VIP lists. If you appear on a VIP list, the bouncer (enforcer) will let you in. It doesn’t matter what your role in life is…

- Then someone realized that the right to do something (authorizations, entitlements…) should rather be linked to a role. Bus drivers can drive public transports buses. The role is bus driver. It is simpler and safer to use a role as an abstraction away from the user. It effectively decouples user lists (which is really about authentication) from entitlements (the right to drive a bus in that case). Entitlements can be more efficiently managed.

- But today’s environments are so **complex** and so **rich** that it is no longer enough. Take the bus analogy again. There are many different buses, different routes, different schedules. What if you want to express the following access control rules:

- Bus drivers can only drive buses between 9AM and 5PM on buses they are qualified to drive. With RBAC, you would have roles e.g. small-bus-morning-driver, medium-bus-morning-driver, big-bus-morning-driver… You get the picture. In this case, the size of the bus (small, medium, big) and the time of the day (morning, evening, night) are examples of attributes one wants to use to express finer-grained rules. *This is where ABAC comes in*.

- The evolution from RBAC to ABAC is very well explained in the following presentation:

**What does XACML bring to the table?**

XACML brings 3 things:

1. an architecture: this helps understand where XACML fits in an existing architecture and how & where access control can be enforced
2. a policy language: means to express access control rules in a standardized, interoperable, way. “Children can eat ice cream”
3. a request / response language: the means to ask questions and to receive an answer in a standardized way: Can naughty children eat ice cream? No, they cannot.

**Architecture overview**

The XACML architecture contains the following points:

1. **the Policy Decision Point (PDP)**: the core of the architecture where policies are evaluated and decisions are reached. The PDP is to XACML what the Supreme Court is to US law.
2. **the Policy Enforcement Point (PEP)**: the integration items which can be anywhere in an application architecture. PEPs are extremely versatile depending on where they enforce access control. The PEP is to XACML and the PDP what police officers and judges are to courts and the law
3. **the Policy Retrieval Point (PRP)**: this is the point through which policies are read from the policy repository. Policy retrieval points ensure the independence of XACML from specific storage mechanisms. The PRP is to XACML what a legislation is to a legal framework: the place where law is written and maintained.
4. **The Policy Information Point (PIP)**: in ABAC and XACML, there is a need for attributes. Attributes are there to describe users, services, resources, actions, and the environment. Policy Information Points are attribute stores. They can be any format and located anywhere. PIPs are to XACML what the DVLA, the set of police records, the census bureau, etc… are to a nation and its citizens.
5. **The Policy Administration Point (PAP)**: this is where you manage your policies.