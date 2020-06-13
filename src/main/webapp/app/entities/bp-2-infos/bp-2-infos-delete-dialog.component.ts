import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IBp2Infos } from 'app/shared/model/bp-2-infos.model';
import { Bp2InfosService } from './bp-2-infos.service';

@Component({
  templateUrl: './bp-2-infos-delete-dialog.component.html',
})
export class Bp2InfosDeleteDialogComponent {
  bp2Infos?: IBp2Infos;

  constructor(protected bp2InfosService: Bp2InfosService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.bp2InfosService.delete(id).subscribe(() => {
      this.eventManager.broadcast('bp2InfosListModification');
      this.activeModal.close();
    });
  }
}
