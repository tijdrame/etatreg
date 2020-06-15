import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IDechargement } from 'app/shared/model/dechargement.model';
import { DechargementService } from './dechargement.service';

@Component({
  templateUrl: './dechargement-delete-dialog.component.html',
})
export class DechargementDeleteDialogComponent {
  dechargement?: IDechargement;

  constructor(
    protected dechargementService: DechargementService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.dechargementService.delete(id).subscribe(() => {
      this.eventManager.broadcast('dechargementListModification');
      this.activeModal.close();
    });
  }
}
