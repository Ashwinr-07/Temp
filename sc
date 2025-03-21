// AddTeamPermissionsModal.js
import React, { useState, useEffect } from "react";
import { ImCross } from "react-icons/im";
import { Button, Pagination } from "@mui/material";
import ModalTemplate from "../ModalTemplate";
import CloudCoreSearchBar from "../CustomStyledComponents/CloudCoreSearchBar";
import { ShoppingCart, CheckCircle } from "@mui/icons-material";
import { useDispatch, useSelector } from "react-redux";
import { toggleShowAddTeamPermissionModal } from "../../store/slices/ModalManager";

const AddTeamPermissionsModal = () => {
  const dispatch = useDispatch();

  const modalHeading = useSelector(
    (state) => state.modalManager.addTeamPermissionModal.heading
  );
  const memberName = useSelector(
    (state) => state.modalManager.addTeamPermissionModal.memberName
  );
  const show = useSelector(
    (state) => state.modalManager.addTeamPermissionModal.show
  );

  if (!show) return null;

  // Hardcoded list of 16 permissions
  const hardcodedPermissions = [
    { id: 1, name: "Permission 1", description: "Description 1" },
    { id: 2, name: "Permission 2", description: "Description 2" },
    { id: 3, name: "Permission 3", description: "Description 3" },
    { id: 4, name: "Permission 4", description: "Description 4" },
    { id: 5, name: "Permission 5", description: "Description 5" },
    { id: 6, name: "Permission 6", description: "Description 6" },
    { id: 7, name: "Permission 7", description: "Description 7" },
    { id: 8, name: "Permission 8", description: "Description 8" },
    { id: 9, name: "Permission 9", description: "Description 9" },
    { id: 10, name: "Permission 10", description: "Description 10" },
    { id: 11, name: "Permission 11", description: "Description 11" },
    { id: 12, name: "Permission 12", description: "Description 12" },
    { id: 13, name: "Permission 13", description: "Description 13" },
    { id: 14, name: "Permission 14", description: "Description 14" },
    { id: 15, name: "Permission 15", description: "Description 15" },
    { id: 16, name: "Permission 16", description: "Description 16" }
  ];

  // Hardcoded team member's permissions (assume they already have 5)
  const memberPermissions = [
    "Permission 12",
    "Permission 14",
    "Permission 15",
    "Permission 16",
    "Permission 11"
  ];

  // Reorder: first show non-assigned permissions then the 5 assigned ones
  const nonMemberPermissions = hardcodedPermissions.filter(
    (p) => !memberPermissions.includes(p.name)
  );
  const memberAssignedPermissions = hardcodedPermissions.filter((p) =>
    memberPermissions.includes(p.name)
  );
  const combinedPermissions = [...nonMemberPermissions, ...memberAssignedPermissions];

  // Local state for search, selected (to simulate toggling), and pagination
  const [searchValue, setSearchValue] = useState("");
  const [selectedPermissions, setSelectedPermissions] = useState([]);
  const [filteredPermissions, setFilteredPermissions] = useState(combinedPermissions);

  // Pagination state
  const [page, setPage] = useState(1);
  const fixedRowsPerPage = 4;
  const totalPages = Math.ceil(filteredPermissions.length / fixedRowsPerPage);

  useEffect(() => {
    const formattedSearch = searchValue.toLowerCase();
    const filtered = combinedPermissions.filter((permission) => {
      const nameMatched = permission.name.toLowerCase().includes(formattedSearch);
      const descriptionMatched = permission.description.toLowerCase().includes(formattedSearch);
      return nameMatched || descriptionMatched;
    });
    setFilteredPermissions(filtered);
    setPage(1);
  }, [searchValue, combinedPermissions]);

  const handleChangePage = (event, value) => {
    setPage(value);
  };

  const permissionsOnCurrentPage = () => {
    if (filteredPermissions.length === 0) {
      return (
        <div className="no-permissions">
          <span className="no-permissions-text">No permissions found</span>
        </div>
      );
    }

    const startIndex = fixedRowsPerPage * (page - 1);
    const endIndex = Math.min(filteredPermissions.length, fixedRowsPerPage * page);

    return filteredPermissions.slice(startIndex, endIndex).map((permission) => {
      // Check if this permission is already assigned
      const isAssigned = memberPermissions.includes(permission.name);
      return (
        <div key={`permission-${permission.id}`} className="permission-tile">
          <div className="permission-info">
            <div className="permission-name">{permission.name}</div>
            <div className="permission-description">{permission.description}</div>
          </div>
          <div className="permission-icon">
            {isAssigned ? (
              <CheckCircle style={{ color: "green" }} />
            ) : (
              <ShoppingCart />
            )}
          </div>
          <div className="permission-action">
            <button
              onClick={() => {
                // Toggle selection for demo purposes
                let updatedSelected = [...selectedPermissions];
                const index = updatedSelected.findIndex((p) => p.name === permission.name);
                if (index !== -1) {
                  updatedSelected.splice(index, 1);
                } else {
                  updatedSelected.push(permission);
                }
                setSelectedPermissions(updatedSelected);
                console.log("Toggled permission:", permission.name, updatedSelected);
              }}
            >
              Toggle
            </button>
          </div>
        </div>
      );
    });
  };

  const closeModalCallback = () => {
    dispatch(toggleShowAddTeamPermissionModal({ show: false }));
  };

  const confirmPermissionUpdate = () => {
    console.log("Confirming permissions update for member:", memberName);
    console.log("Selected permissions:", selectedPermissions);
    closeModalCallback();
  };

  return (
    <ModalTemplate closeCallback={closeModalCallback}>
      <div className="modal-header">
        <div className="modal-title">{modalHeading}</div>
        <ImCross onClick={closeModalCallback} className="modal-close" />
      </div>
      <div className="modal-body">
        <div className="modal-search-pagination">
          <CloudCoreSearchBar
            placeholder="Permission name"
            value={searchValue}
            onChange={(e) => setSearchValue(e.target.value)}
          />
          <Pagination
            variant="outlined"
            shape="rounded"
            boundaryCount={1}
            siblingCount={1}
            page={page}
            onChange={handleChangePage}
            count={totalPages}
          />
        </div>
        <div className="modal-content">{permissionsOnCurrentPage()}</div>
      </div>
      <div className="modal-footer">
        <Button variant="contained" color="error" onClick={closeModalCallback}>
          Cancel
        </Button>
        <Button type="button" variant="outlined" onClick={confirmPermissionUpdate}>
          Update Permissions
        </Button>
      </div>
    </ModalTemplate>
  );
};

export default AddTeamPermissionsModal;
